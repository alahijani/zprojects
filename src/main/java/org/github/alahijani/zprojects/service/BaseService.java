package org.github.alahijani.zprojects.service;

import org.github.alahijani.zprojects.model.BaseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@Repository
@Transactional
public abstract class BaseService<E extends BaseEntity> {

    protected final Class<E> entityClass;
    private final PropertyEditorSupport propertyEditor = new EntityPropertyEditor();

    @PersistenceContext
    protected EntityManager em;

    @PostConstruct
    void postConstruct() {
    }

    protected BaseService(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * persists the given entity if it is transient, or updates the database if the entity is persistent.
     *
     * @param entity the entity to save
     * @return the same entity after save
     */
    public E save(E entity) {
        return em.merge(entity);
    }

    @Nonnull
    public E findById(String id) throws NoResultException {
        E entity = em.find(entityClass, id);
        if (entity == null)
            throw new NoResultException(entityClass.getName() + "#" + id);

        return entity;
    }

    public E getReference(String id) {
        return em.getReference(entityClass, id);
    }

    /**
     * @return the collection of all entities of this type saved in the database
     */
    public abstract List<E> findAll();

    public void registerCustomEditor(WebDataBinder binder) {
        binder.registerCustomEditor(entityClass, propertyEditor);
    }

    /**
     * @author Ali Lahijani
     */
    private class EntityPropertyEditor extends PropertyEditorSupport {

        /**
         * Converts a String to an entity (when submitting form)
         *
         * @param text interpreted as {@link BaseEntity#id database ID}
         */
        @Override
        public void setAsText(String text) {
            E entity = (text == null || text.isEmpty()) ? null : getReference(text);
            setValue(entity);
        }

        /**
         * Converts an entity to a String (when displaying form)
         *
         * @return a {@link BaseEntity#id database ID}
         */
        @Override
        public String getAsText() {
            E entity = entityClass.cast(getValue());
            return entity == null ? null : entity.getId();
        }
    }

}

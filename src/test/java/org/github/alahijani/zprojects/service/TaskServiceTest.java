package org.github.alahijani.zprojects.service;

import junit.framework.Assert;
import org.github.alahijani.zprojects.model.Project;
import org.github.alahijani.zprojects.model.Task;
import org.github.alahijani.zprojects.model.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ali Lahijani
 */
public class TaskServiceTest extends BaseServiceTest {

    @Resource
    TaskService service;
    @Resource
    ProjectService projectService;
    @Resource
    UserService userService;

    @Test
    public void testFindAll() throws Exception {
        Project project = new Project();
        project.setCode("IXX-N5e");
        project.setTitle("N5e Project IXX");
        project.setDescription("Lorem ipsum dolor sit amet.");
        project = projectService.save(project);

        Task task = new Task();
        task.setTitle("Ullamco Laboris");
        task.setDescription("Ut enim ad minim veniam");
        task.setProject(project);
        service.save(task);

        List<Task> tasks = service.findAll(project);
        Assert.assertNotNull(tasks);
        Assert.assertEquals(tasks.size(), 1);
        Assert.assertEquals(tasks.get(0).getDescription(), "Ut enim ad minim veniam");
    }

    @Test
    public void testFindVisible() throws Exception {
        Project project = new Project();
        project.setCode("IXX-N5e");
        project.setTitle("N5e Project IXX");
        project.setDescription("Lorem ipsum dolor sit amet.");
        project = projectService.save(project);

        User user = new User();
        user.setUsername("jimmy-123-1");
        user.setPassword("@#$D!@E@8j");
        user.setFullName("Jimmy");
        user.setAdmin(false);
        user = userService.save(user);

        Task task = new Task();
        task.setTitle("Ullamco Laboris");
        task.setDescription("Ut enim ad minim veniam");
        task.setProject(project);
        task.setAssignee(user);
        service.save(task);

        List<Task> tasks = service.findVisible(project, user.getUsername());
        Assert.assertNotNull(tasks);
        Assert.assertEquals(tasks.size(), 1);
        Assert.assertEquals(tasks.get(0).getDescription(), "Ut enim ad minim veniam");

        tasks = service.findVisible(user.getUsername());
        Assert.assertNotNull(tasks);
        Assert.assertEquals(tasks.size(), 1);
        Assert.assertEquals(tasks.get(0).getDescription(), "Ut enim ad minim veniam");
    }

}


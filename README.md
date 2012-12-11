Functional specification of project
===================================

Application should allow creation and management of tasks.
Application should consist from 2 parts:

1. Administration module
2. User module

Domain entities of the application are:

1. User - fields’ definition is up to implementer
2. Project - fields’ definition is up to implementer
3. Task - fields’ definition is up to implementer

Application should allow two type of users:

1. Administrator - Is allowed to:
   * Create Users
   * Create Projects
   * Create Tasks per Project, each Task can be assigned only to 1 Project
   * Assign Tasks to Users, each Task can be assigned only to 1 User

2. Regular user - Is allowed to:
   * See Tasks assigned to him (per Project)
   * Edit Task's fields (status, description, etc ...), reassign Tasks to other NOT administrator Users


Implementation details

1. Project should be implemented as web application
2. Whatever web applications frameworks can be used (JSP, JSF, Struts, Wicket, Spring MVC, etc ...)
3. Use of ORM (Object Relational Mapping) for database related activities is highly recommended (Hibernate, iBatis, etc... )
4. Use of IOC (Inversion Of Control) containers for application initial startup and DI (Dependency Injection) is highly recommended (Spring, Google Guice, etc ...)
5. Use of TDD (Test Driven Development) practices is highly recommended (Junit, etc ...)
6. Please use best coding practices you are able (formatting, comments, etc ...)

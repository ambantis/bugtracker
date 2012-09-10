bugtracker
==========

A web application used to track application defects (i.e., bugs).

Requirements
------------

The defect tracking system is a communication tool used by staff to track bug requests from
submission to closure against an application.

The system provides several features:

 * Submit a defect against an application;
 * Update an existing defect;
 * Assign a defect request to a person;
 * Email status information to a user, and
 * View a list of all open defects.

The system is available via the Internet with a user-friendly interface.

Implementation Notes
--------------------

This application assumes use of tomcat7 web container. The following lines should be added to your
tomcat-users.xml file for implementation of the web app:

    <!-- This section contains roles and users for the bugtracker web app -->
      <role rolename="bug-qa"/>
      <role rolename="bug-mngr"/>
      <role rolename="bug-dev"/>
      <user username="acbantis@gmail.com" password="sunshine" roles="bug-mngr"/>
      <user username="ambantis@gmail.com" password="jonathan" roles="bug-mngr"/>
      <user username="annevo101@yahoo.com" password="allyson" roles="bug-dev"/>
      <user username="lloydmxl@gmail.com" password="hellojstl" roles="bug-dev"/>
      <user username="a.bantis@lausd.net" password="lausd" roles="bug-qa"/>
      <user username="anbant2010@gmail.com" password="athena" roles="bug-qa"/>
    <!-- End of section for roles/users of the bugtracker web app -->

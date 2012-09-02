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

<pre>
    <!-- This section contains roles and users for the bugtracker web app -->
      <role rolename="bug-qa"/>
      <role rolename="bug-mngr"/>
      <role rolename="bug-dev"/>
      <user username="marge" password="kiss" roles="bug-mngr"/>
      <user username="burns" password="money" roles="bug-mngr"/>
      <user username="bart"  password="cowabunga" roles="bug-dev"/>
      <user username="lisa"  password="flute" roles="bug-dev"/>
      <user username="barney" password="burp" roles="bug-qa"/>
      <user username="homer" password="donuts" roles="bug-qa"/>
    <!-- End of section for roles/users of the bugtracker web app -->
</pre>

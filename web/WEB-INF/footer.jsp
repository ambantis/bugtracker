

<%@ page import="java.util.*" %>

<% GregorianCalendar currentDate = new GregorianCalendar();
int currentYear = currentDate.get(Calendar.YEAR);
%>

<p><small>
&copy; Copyright <%= currentYear %> UCI Java Class II
</small></p>
</body>
</html>

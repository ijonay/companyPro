<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>诊断</title>
</head>
<body>
<strong>java.io.tmpdir:</strong>
<ul>
<li><%=System.getProperty("java.io.tmpdir")%></li>
</ul>
<br/>
<strong>Memory:</strong>
<ol>
<li>availableProcessors:<%= Runtime.getRuntime().availableProcessors() %></li>
<li>freeMemory=<%=Runtime.getRuntime().freeMemory()/(1024*1024)%>M</li>
    <li>totalMemory=<%=Runtime.getRuntime().totalMemory()/(1024*1024)%>M</li>
    <li>maxMemory=<%=Runtime.getRuntime().maxMemory()/(1024*1024)%>M</li>
</ol>
<br/>
<strong>Thread:</strong>
<ol>
<%for(Thread t : list_threads()){%>
<li>tid:<%=t.getId() %>,<%=t.getName()%>(<b><%=t.getState()%></b>) : <%=t.getClass().getName()%></li>
<%}%>
</ol>
<%!
public static java.util.List<Thread> list_threads(){
    int tc = Thread.activeCount();
    Thread[] ts = new Thread[tc];
    Thread.enumerate(ts);
    return java.util.Arrays.asList(ts);
}
%>
</body>
</html>
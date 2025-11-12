<%@ page session="true" %>
<%
  if (session.getAttribute("userId") == null) {
    response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
    return;
  }
%>
<html>
<head>
  <title>Take Exam</title>
</head>
<body>
  <div>Timer: <span id="timer">loading...</span></div>

  <!-- Questions area: in a real app render questions dynamically; here we show placeholder -->
  <div id="questions">
    <!-- For demo: you can fetch questions by AJAX if you created them in DB. -->
  </div>

  <button id="submitBtn">Submit Exam</button>

<script>
const attemptId = sessionStorage.getItem('attemptId');
const endTime = parseInt(sessionStorage.getItem('endTime'), 10); // millis

function startTimer() {
  function tick(){
    const now = Date.now();
    let diff = endTime - now;
    if (diff <= 0) {
      document.getElementById('timer').innerText = "00:00:00";
      autoSubmit();
      return;
    }
    const sec = Math.floor(diff/1000);
    const h = Math.floor(sec/3600); 
    const m = Math.floor((sec%3600)/60);
    const s = sec%60;
    document.getElementById('timer').innerText = 
      String(h).padStart(2,'0') + ':' + String(m).padStart(2,'0') + ':' + String(s).padStart(2,'0');
  }
  tick();
  setInterval(tick, 1000);
}

// Hook submit
document.getElementById('submitBtn').addEventListener('click', function(){
  fetch('${pageContext.request.contextPath}/finalSubmit', {
    method: 'POST',
    headers: {'Content-Type':'application/x-www-form-urlencoded'},
    body: 'attemptId=' + encodeURIComponent(attemptId)
  }).then(r => r.json()).then(j => {
    // redirect to results
    window.location = '${pageContext.request.contextPath}/jsp/results.jsp?attemptId=' + attemptId;
  }).catch(e => alert('Submit failed: ' + e));
});

// Example autosave function (to be called when an option changed)
function autosave(questionId, selectedCsv) {
  fetch('${pageContext.request.contextPath}/saveAnswer', {
    method: 'POST',
    headers: {'Content-Type':'application/x-www-form-urlencoded'},
    body: 'attemptId=' + encodeURIComponent(attemptId) +
          '&questionId=' + encodeURIComponent(questionId) +
          '&selected=' + encodeURIComponent(selectedCsv)
  }).then(r => r.json()).then(j => {
    // ok
  }).catch(e => console.log('autosave err', e));
}

startTimer();
</script>
</body>
</html>

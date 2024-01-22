document.getElementById("myForm").addEventListener("submit", function(event) {
    event.preventDefault();

    fetch('SvInit', {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            window.location.href = 'JSP/newUser.jsp';
        }
    })
    .catch(error => {
        console.error('Error in fetch:', error);
    });
});
window.onload = function() {
    console.log("The page loaded!");
    let button = document.getElementById('login-button');
    button.addEventListener("click", login)

    let passwordField = document.getElementById('password');
    passwordField.addEventListener('keyup', function(e) {
        if (e.key === 'Enter') {
            login();
        }
    });
}

function login() {
    
    let usernameInput = document.getElementById('username');
    let passwordInput = document.getElementById('password');
    let errorContainer = document.getElementById('error-message');


    let username = usernameInput.value;
    let password = passwordInput.value;

    if (username && password) {

        errorContainer.setAttribute('hidden', true);

        fetch('http://localhost:8080/shoe_collector/auth', {

            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({username, password})
        });

    } else {

        errorContainer.removeAttribute('hidden');
        errorContainer.innerText = "You must provide a username and password";

    }

}
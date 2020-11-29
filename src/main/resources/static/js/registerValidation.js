let checkmark = document.getElementsByClassName('complete');


function validateEmail(data) {
    let testData = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(testData.test(data)) {
        return true;
    }
    return (false)
}

function disableButton() {
    document.getElementById('submit').disabled = true;
}

function enableButton() {
    document.getElementById('submit').disabled = false;
}

document.getElementById('name').onblur = function() {
    let status = document.getElementById('name').value;
    if (status.length < 1) {
        document.getElementById('nameHelp').innerHTML = 'Imię nie może być puste!';
        checkmark[0].classList.remove('active');
        disableButton();
    } else {
        document.getElementById('nameHelp').innerHTML = '';
        checkmark[0].classList.add('active');
        enableButton();
    }
};

document.getElementById('lastName').onblur = function() {
    let status = document.getElementById('lastName').value;
    if (status.length < 1) {
        document.getElementById('lastNameHelp').innerHTML = 'Nazwisko nie może być puste';
        checkmark[1].classList.remove('active');
        disableButton();
    } else {
        document.getElementById('lastNameHelp').innerHTML = '';
        checkmark[1].classList.add('active');
        enableButton();
    }
};

document.getElementById('email').onblur = function() {
    let status = document.getElementById('email').value;
    if (status.length < 1) {
        document.getElementById('emailHelp').innerHTML = 'Adres email nie może być puste';
        checkmark[2].classList.remove('active');
        disableButton();
    }else if (!validateEmail(status)) {
        document.getElementById('alert-email').innerHTML = 'Adres email jest nieprawidłowy ';
        checkmark[2].classList.remove('active');
        disableButton();
    }  else {
        document.getElementById('emailHelp').innerHTML = '';
        checkmark[2].classList.add('active');
        enableButton();
    }
};

document.getElementById('password').onblur = function() {
    let status = document.getElementById('password').value;
    if (status.length < 1) {
        document.getElementById('passwordHelp').innerHTML = 'Hasło powinno zawierać dużą i małą literę, cyfrę oraz jeden ze znaków !, @, #, $';
        checkmark[3].classList.remove('active');
        disableButton();
    } else {
        document.getElementById('passwordHelp').innerHTML = '';
        checkmark[3].classList.add('active');
        enableButton();
    }

    document.getElementById('passwordConfirm').onblur = function() {
        let confirm = document.getElementById('passwordConfirm').value;
        if(status != confirm) {
            document.getElementById('passwordConfirmdHelp').innerHTML = "Hasła nie są identyczne";
            checkmark[4].classList.remove('active');
            disableButton();
        } else {
            document.getElementById('passwordConfirmdHelp').innerHTML = '';
            checkmark[4].classList.add('active');
            enableButton();
        }
    }
}

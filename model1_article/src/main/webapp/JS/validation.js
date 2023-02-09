
function validateForm() {
    var author = document.forms["registerForm"]["author"].value;
    var password = document.forms["registerForm"]["password"].value;
    var confirm_password = document.forms["registerForm"]["confirm_password"].value;
    var title = document.forms["registerForm"]["title"].value;
    var content = document.forms["registerForm"]["content"].value;

    if (author.length < 3 || author.length >= 5) {
        alert("작성자는 3글자 이상 5글자 미만으로 적어주세요");
        return false;
    }

    if (password.length < 4 || password.length >= 16) {
        alert("비밀번호는 4글자 이상 16글자 미만으로 적어주세요");
        return false;
    }

    if (password != confirm_password) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
        return false;
    }

    if (title.length < 4 || title.length >= 100) {
        alert("제목은 4글자 이상 100글자 미만으로 적어주세요");
        return false;
    }

    if (content.length < 4 || content.length >= 2000) {
        alert("내용은 4글자 이상 2000글자 미만으로 적어주세요");
        return false;
    }
}
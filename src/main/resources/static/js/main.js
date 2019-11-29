'use strict';

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');


function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
        formData.append("type", "qulaquer");
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/upload");

    xhr.send(formData);

    xhr.onload = function() {
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";

        var url = "https://storage.cloud.google.com/instaltec_store/temp.xlsx?authuser=0";
        a.href = url;
        a.click();
        window.URL.revokeObjectURL(url);
    }


}

multipleUploadForm.addEventListener('submit', function(event){
    var files = multipleFileUploadInput.files;
    if(files.length === 0) {
        multipleFileUploadError.innerHTML = "Please select at least one file";
        multipleFileUploadError.style.display = "block";
    }
    uploadMultipleFiles(files);
    event.preventDefault();
}, true);
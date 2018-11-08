function AdminUserServiceClient($http) {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'https://frozen-escarpment-61642.herokuapp.com/';
    var self = this;


    function createUser(user) {
        var promiseObj = new Promise(function(resolve, reject){
            var xhr = new XMLHttpRequest();
            xhr.open("GET", self.url+"data/single-user.json", true);
            xhr.send();
            xhr.onreadystatechange = function(){
                if (xhr.readyState === 4){
                    if (xhr.status === 200){
                        var resp = xhr.responseText;
                        var respJson = JSON.parse(resp);
                        resolve(respJson);
                    } else {
                        reject(xhr.status);
                    }
                }
            }
        });
        return promiseObj;
    }

    function findAllUsers() {
        var promiseObj = new Promise(function(resolve, reject){
            var xhr = new XMLHttpRequest();
            xhr.open("GET", self.url+"data/user-data.json", true);
            xhr.send();
            xhr.onreadystatechange = function(){
                if (xhr.readyState === 4){
                    if (xhr.status === 200){
                        var resp = xhr.responseText;
                        var respJson = JSON.parse(resp);
                        resolve(respJson);
                    } else {
                        reject(xhr.status);
                    }
                }
            }
        });
        return promiseObj;
    }



    function findUserById(userId) {
        var promiseObj = new Promise(function(resolve, reject){
            var xhr = new XMLHttpRequest();
            xhr.open("GET", self.url+"data/single-user.json", true);
            xhr.send();
            xhr.onreadystatechange = function(){
                if (xhr.readyState === 4){
                    if (xhr.status === 200){
                        var resp = xhr.responseText;
                        var respJson = JSON.parse(resp);
                        resolve(respJson);
                    } else {
                        reject(xhr.status);
                    }
                }
            }
        });
        return promiseObj;
    }

    function updateUser(userId, user) {
        var promiseObj = new Promise(function(resolve, reject){
            var xhr = new XMLHttpRequest();
            xhr.open("GET", self.url+"data/single-user.json", true);
            xhr.send();
            xhr.onreadystatechange = function(){
                if (xhr.readyState === 4){
                    if (xhr.status === 200){
                        var resp = xhr.responseText;
                        var respJson = JSON.parse(resp);
                        resolve(respJson);
                    } else {
                        reject(xhr.status);
                    }
                }
            }
        });
        return promiseObj;
    }

    function deleteUser(userId) {
        return true;
    }
}

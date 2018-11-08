(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $updateBtn, $createBtn, $searchBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $logo;
    var $userRowTemplate, $tbody, $selectedUser;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");
        $logo = $("#user-admin-logo");
        
        $userRowTemplate = $(".wbdv-template.wbdv-user");
        $tbody =$(".wbdv-tbody");

        $createBtn = $("#createBtn");
        $updateBtn = $("#updateBtn");
        $searchBtn = $("#searchBtn");

        $createBtn.click(createUser);
        $updateBtn.click(updateUser);
        $searchBtn.click(searchUser);
        $logo.click(refreshPage);

        findAllUsers().then(function (users) {
            renderUsers(users);
        });
    }

    function refreshPage() {
        window.location.reload();
    }

    function clearFormFields(){
        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");
    }

    function createUser() {
        var userName = $usernameFld.val();
        var password = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var role = $roleFld.val();

        clearFormFields();

        var timestamp = (new Date()).getTime();
        var newUser = $userRowTemplate.clone();
        newUser
            .removeClass("wbdv-hidden")
            .attr("id", timestamp)
            .find(".wbdv-username")
            .html(userName);
        newUser
            .find(".wbdv-password")
            .html(password);
        newUser
            .find(".wbdv-first-name")
            .html(firstName);
        newUser
            .find(".wbdv-last-name")
            .html(lastName);
        newUser
            .find(".wbdv-role")
            .html(role)

        newUser
            .find(".wbdv-remove")
            .click(deleteUser);

        newUser
            .find(".wbdv-edit")
            .click(selectUser);

        $tbody.append(newUser);
    }

    function findAllUsers() {
        return userService.findAllUsers().then();
    }

    function findUserById(uid) {
        return userService.findUserById(uid).then();
    }

    function deleteUser(event) {
        var button = $(event.currentTarget);
        var tr = button.parents(".wbdv-template");
        var uid = tr[0].id;

        tr.remove();
        clearFormFields();
        userService.deleteUser(uid);
    }

    function selectUser(event) {
        var button = $(event.currentTarget);
        $selectedUser = button.parents(".wbdv-template");

        $usernameFld.val($selectedUser.find(".wbdv-username").html());
        $passwordFld
            .val($selectedUser.find(".wbdv-password").html());
        $firstNameFld.val($selectedUser.find(".wbdv-first-name").html());
        $lastNameFld.val($selectedUser.find(".wbdv-last-name").html());
        $roleFld.val($selectedUser.find(".wbdv-role").html());

    }

    function searchUser() {
        var userName = $usernameFld.val().trim().toLowerCase();
        var password = $passwordFld.val().trim().toLowerCase();
        var firstName = $firstNameFld.val().trim().toLowerCase();
        var lastName = $lastNameFld.val().trim().toLowerCase();
        var role = $roleFld.val();

        var rows = $tbody.children().toArray();
        for (var i in rows){
            if (!$(rows[i]).attr("class").includes("wbdv-hidden"))
            {
                var curUserName = $(rows[i]).find(".wbdv-username").html().trim().toLowerCase();
                var curFirstName = $(rows[i]).find(".wbdv-first-name").html().trim().toLowerCase();
                var curLastName = $(rows[i]).find(".wbdv-last-name").html().trim().toLowerCase();
                var curPassword = $(rows[i]).find(".wbdv-password").html().trim().toLowerCase();
                var curRole = $(rows[i]).find(".wbdv-role").html();

                if ((!userName ||  userName == curUserName) && (!password ||  password == curPassword) && (!firstName || firstName==curFirstName) && (!lastName || lastName==curLastName) && (!role || role == curRole))
                    $(rows[i]).show();
                else
                    $(rows[i]).hide();
            }
        }
    }

    function updateUser() {
        if (!$selectedUser) {
            return;
        }

        var user = { id: $selectedUser.attr("id"),
                    username: $usernameFld.val(),
                    password: $passwordFld.val(),
                    firstName: $firstNameFld.val(),
                    lastName: $lastNameFld.val(),
                    role: $roleFld.val()};

        $selectedUser.find(".wbdv-username").html(user.username);
        $selectedUser.find(".wbdv-password").html(user.password);
        $selectedUser.find(".wbdv-first-name").html(user.firstName);
        $selectedUser.find(".wbdv-last-name").html(user.lastName);
        $selectedUser.find(".wbdv-role").html(user.role);
        $selectedUser = null;
        clearFormFields();

        userService.updateUser(user.id,user);
    }

    function renderUser(user) {
        var newUser = $userRowTemplate.clone();

        newUser
            .removeClass("wbdv-hidden")
            .attr("id", user.id)
            .find(".wbdv-username")
            .html(user.username);
        newUser
            .find(".wbdv-password")
            .html(user.password);
        newUser
            .find(".wbdv-first-name")
            .html(user.firstName);
        newUser
            .find(".wbdv-last-name")
            .html(user.lastName);
        newUser
            .find(".wbdv-role")
            .html(user.role);

        newUser
            .find(".wbdv-remove")
            .click(deleteUser);

        newUser
            .find(".wbdv-edit")
            .click(selectUser);

        $tbody.append(newUser);
    }

    function renderUsers(users) {
        for (var i in users){
            renderUser(users[i]);
        }
    }
})();

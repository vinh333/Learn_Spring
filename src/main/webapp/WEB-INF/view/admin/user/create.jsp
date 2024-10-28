<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>

                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    }); 
                </script>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Create a user</h3>
                            <hr />
                            <!-- Form login -->
                            <form:form method="post" action="/admin/user/create" enctype="multipart/form-data"
                                modelAttribute="newUser">
                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="exampleInputEmail1" class="form-label">Email address</label>
                                        <form:input type="email" class="form-control" aria-describedby="emailHelp"
                                            path="email" />
                                    </div>
                                    <div class="col">
                                        <label for="exampleInputPassword1" class="form-label">Password</label>
                                        <form:input type="password" class="form-control" path="password" />
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="exampleInputPassword1" class="form-label">Phone Number</label>
                                        <form:input type="text" class="form-control" path="phone" />
                                    </div>
                                    <div class="col">
                                        <label for="exampleInputPassword1" class="form-label">Full Name</label>
                                        <form:input type="text" class="form-control" path="fullName" />
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="exampleInputPassword1" class="form-label">Address</label>
                                        <form:input type="text" class="form-control" path="address" />
                                    </div>

                                </div>

                                <div class="row mb-3">
                                    <div class="col">
                                        <label for="exampleInputPassword1" class="form-label">Role</label>
                                        <form:select class="form-select" path="role.name">
                                            <form:option value="ADMIN">ADMIN</form:option>
                                            <form:option value="USER">USER</form:option>
                                        </form:select>
                                    </div>
                                    <div class="col">
                                        <label for="avatarFile" class="form-label">Avatar :</label>
                                        <input class="form-control" type="file" id="avatarFile" name="hoidanitFile"
                                            accept=".png, .jpg, .jpeg" />


                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <img style="max-height: 250px; display: none;" alt="avatar preview"
                                        id="avatarPreview" />
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form:form>
                        </div>

                    </div>
                </div>

            </body>

            </html>
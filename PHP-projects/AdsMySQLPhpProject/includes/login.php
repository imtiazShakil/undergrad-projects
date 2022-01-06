<?php

    $name= $_POST['nam'];
    $pass= $_POST['pass'];

    include_once ('db.php');

    $ans=  mysql_query("select * from loginTable where user='$name' and password='$pass';");

    if($ans==FALSE) echo("Login Failed");
    else if(mysql_num_rows($ans)<1) echo("Login Failed");
    else echo("Login Successful");
?>

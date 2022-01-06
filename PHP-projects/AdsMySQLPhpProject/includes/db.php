<?php
	$connection = mysql_connect('localhost','admin','admin');
	mysql_select_db('test',$connection);
	
        if(!$connection){
		echo("Failed to connect</br>");
	}else echo("Connection With The Database is Successful</br>");
?>
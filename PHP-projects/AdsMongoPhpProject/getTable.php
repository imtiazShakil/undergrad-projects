<?php

    include_once ('getConnection.php');
    $dbname=$_POST['query'];
//    $dbname="testdb";
    
    $dbb = $conn->$dbname;
    
    $collList = $dbb->getCollectionNames();

    echo("<table border=\"2\">");
    foreach($collList as $v) {
        echo("<tr><td>".$v."</tr></td>");
    }
    echo("</table>");
?>

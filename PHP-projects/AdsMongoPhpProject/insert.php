<?php
    include_once ('getConnection.php');

    
//    $dbName=$_POST['InsdbName'];
//    $collectionName= $_POST['InstableName'];
    
//    $db = $conn->$dbName;
//    $collection = $db->$collectionName;
    
    $dbName;
    $collectionName;
    
    $arr =Array();
    $i=0;
    $p;
    foreach($_POST as $key => $value)
    {
        if($i==0) $dbName=$value;
        if($i==1) $collectionName=$value;
        else if( ($i % 2) == 0 ) $p=$value;
        else if($i%2) $arr[$p]=$value;
        
        $i++;
//        echo($key." ".$value."\n");
    }
    
//    foreach ($arr as $k => $v) 
//    {
//        echo($k." ".$v);
//    }
    $db= $conn->$dbName;
    $collection= $db->$collectionName;
    
    $collection->insert($arr);
    
    echo("<h1 align=\"center\">Inserted Successfully</h1>");
    
?>

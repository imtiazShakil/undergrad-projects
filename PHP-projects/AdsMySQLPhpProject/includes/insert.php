<?php
    include_once ('db.php');
    
    $var =array();
    $cnt=0;
    $str="insert into ";
    
    foreach($_POST as $key => $value)
    {
//        echo($str);
        if($cnt==0) $str=$str." ".$value;
        else if($cnt==1) $str=$str." values('".$value."'";
        else $str=$str.", '".$value."'";
                
        $cnt++;
//        $var[$key]=$value;
//        echo("key ".$key." value ".$value);
    }
    $str=$str.");";
    echo($str);
    
    $ans=mysql_query($str);
    if($ans==FALSE) echo("<br/>Insertion failed");
    else echo("<br/>Insertion Succedeed");
?>
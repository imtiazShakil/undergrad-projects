<?php
    include_once ('db.php');
    
    $var =array();
    $cnt=0;
    $str="update ";
    $str2="where ";
    foreach($_POST as $key => $value)
    {
        if($cnt==0) { 
            
            $str=$str." ".$value ." set ";//this is the tablename
            
        }
        else if($cnt==1) 
        {
            $str=$str." ".$key." = '".$value."'" ;
             $str2=$str2." ".$key." = '".$value."'";
        }
        else $str=$str." , ".$key." = '".$value."'" ;
        $cnt++;

        
    }
    $str=$str." ".$str2." ;";
    echo($str);
    
    $ans=mysql_query($str);
    if($ans==FALSE) echo("<br/>Update failed");
    else echo("<br/>Update Succedeed");
?>
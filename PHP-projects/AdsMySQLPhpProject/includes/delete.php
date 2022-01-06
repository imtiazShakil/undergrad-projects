<?php
    include_once ('db.php');
    
    $var =array();
    $cnt=0;
    $str="delete from ";
    
    foreach($_POST as $key => $value)
    {
        echo($key + " "+$value);
        if($cnt==0) {         
            $str=$str." ".$value ." where ";//this is the tablename
            
        }
        else if($cnt==1) 
        {
            $str=$str." ".$key." = '".$value."'" ;
        }

        $cnt++;

        
    }
    $str=$str." ;";
    echo($str);
    
    $ans=mysql_query($str);
    if($ans==FALSE) echo("<br/>Delete Failed");
    else echo("<br/>Delete Succedeed");
?>
<?php
    include_once ('db.php');
    $query=$_POST['quer'];
    
    $ans=  mysql_query($query);
    $var= array();
    while($row=  mysql_fetch_row($ans))
    {
        $i=0;
        while($i<count($row))
        {
            echo($row[$i]);
            array_push($var, $row[$i] );
            $i++;
        }
    }
    echo json_encode($var);
?>
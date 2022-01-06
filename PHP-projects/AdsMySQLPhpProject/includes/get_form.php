<?php

    $connection = mysql_connect('localhost','admin','admin');
    mysql_select_db('test',$connection);
	
    $query=$_POST['query'];
    $ans=  mysql_query($query);
    if($ans==FALSE)        return;
    else {
//        echo("<form id=\"dynamicForm\" action=\"includes/insert.php\" method=\"post\">");
        $var =array();
        while($row=  mysql_fetch_row($ans))
        {
            $i=0;
            while($i<count($row))
            {
                array_push($var, $row[$i]);
//                echo("<label>$row[$i]</label><input type=\"text\" id=\"$row[$i]\" value=\"\"/></br>");
                $i++;
            }
        }
        //echo("<input type=\"submit\" id=\"insertButton\" value=\"Confirm\"/></br>");
//        echo("</from>");
        echo json_encode($var);
        
    }
?>
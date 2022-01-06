<?php
include_once ('db.php');

$query=$_POST['query'];
draw_table($query);

function draw_table($query)
{
        $ans=  mysql_query($query);
        if($ans==FALSE) 
        {
            echo("Invalid Query");
            return ;
        }
        if($ans==TRUE) {
            if(mysql_num_rows($ans)==False)  /// this might be an insert/delete/update/replace operation
            {
                echo("Success");
                return ;
            }
        }

        $i=0;$col_name;
        echo("<thead> <tr>");
        while($i<  mysql_num_fields($ans))
        {
            $col_name=  mysql_fetch_field($ans,$i);
            echo("<th>".$col_name->name ."</th>");
            $i=$i+1;
        }
        echo("</tr> </thead>");

        echo("<tbody>");
        while($row =  mysql_fetch_row($ans))
        {
            $j=0;echo("<tr>");
            while($j<count($row))
            {
                echo("<td>". $row[$j] ."</td>" );
                $j++;
            }
            echo("</tr>");
        }
        echo("</tbody>");
        mysql_free_result($ans);
}
?>
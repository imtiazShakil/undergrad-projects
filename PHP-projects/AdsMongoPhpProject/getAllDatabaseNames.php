<?php
           
            include_once ('getConnection.php');
            $ldb=$conn->listDBs();
            
            
            echo("<table border=\"2\">");
            $ldb2=$ldb['databases'];          
            foreach($ldb2 as $v) 
            {
                $ldb3=$v;
                $ldb4=$ldb3['name'];
                echo("<tr><td>".$ldb4."</td></tr>");
                
            }
            echo("</table>");

?>
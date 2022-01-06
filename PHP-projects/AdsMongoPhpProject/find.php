<?php
    include_once ('getConnection.php');
  
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
    }
    

    $db= $conn->$dbName;
    $collection= $db->$collectionName;
    
    $cursor=$collection->find($arr);
    
    echo "<h1 align=\"center\"> Search Result</h1>";
    echo "<table border=\"2px\">";
    foreach ($cursor as $venue) {
            echo "<tr>";
            foreach (array_slice($venue, 1) as $key => $value) {
               echo "<td>" . $key . "</td>";
               echo "<td>" . $value . "</td>";
            }

            echo "</tr>";
    }
    echo "</table>";
    
?>

<?php
    include_once ('getConnection.php');
    
    $dbName=$_POST['dbName'];
    $collectionName= $_POST['tableName'];
    
    $db = $conn->$dbName;
    $collection = $db->$collectionName;
    
    
    $cursor = $collection->find();
	
    if($cursor!=null) {
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
    }
?>

<?php
    include_once ('getConnection.php');

    
    $dbName;
    $collectionName;
    
    $findObject =Array();
    $newObject = Array();
    $i=0;   $fndp;     $newp;

//    foreach($_POST as $key => $value)
//    {
//        if($i==0) $dbName=$value;
//        if($i==1) $collectionName=$value;
//        else if( (($i-2)%4)==0  ) $fndp=$value;
//        else if( (($i-2)%4)==1 ) $findObject[$fndp]=$value;
//        else if( (($i-2)%4)==2 ) $newp=$value;
//        else if( (($i-2)%4)==3 ) $newObject[$newp]=$value;
//  
////        echo($key." ".$value."\n" );
//        $i++;
//    }
//    $haystack="hudai";
        foreach($_POST as $key => $value)
        {
            if($i==0) $dbName=$value;
            if($i==1) $collectionName=$value;
            else if( exists($key, "fndKey")  ) $fndp=$value;
            else if( exists($key, "fndVal") ) $findObject[$fndp]=$value;
            else if( exists($key, "updKey") ) $newp=$value;
            else if( exists($key, "updVal") ) $newObject[$newp]=$value;

    //        echo($key." ".$value."\n" );
            $i++;
        }
//    echo(strpos($haystack, "da"));

//    foreach ($findObject as $k => $v) 
//    {
//        echo($k." ".$v."\n");
//    }
//    
//    foreach ($newObject as $k => $v) 
//    {
//        echo($k." ".$v."\n");
//    }
    
    
    
    $db= $conn->$dbName;
    $collection= $db->$collectionName;
    
    $collection->update($findObject,$newObject);
    
    echo("<h1 align=\"center\">Updated Successfully</h1>");
    function exists($var,$var2)
    {
        if(strpos($var,$var2)===FALSE)            return 0;
        else            return 1;
    }
?>

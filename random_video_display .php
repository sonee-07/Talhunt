<?php
include('connection.php');
$query="SELECT * FROM `banner_ad` ORDER BY RAND() LIMIT 4";
if($query_run=mysql_query($query))
{
    $i=4;
    while($rows=mysql_fetch_array($query_run))
    {
        echo $rows['banner_no'];
        echo $rows['banner_name'];
        echo "<a href=\"".$rows['Banner_website_url']. "\">";
        echo "<img src=\"".$rows['banner_image_url']."\" width=\"100px\" height=\"100px\">";
        echo"</a>";
    }
} else {
    echo'<font color="red"> Query does not run. </font>';
}
?>
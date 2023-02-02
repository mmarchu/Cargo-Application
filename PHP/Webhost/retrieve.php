<?php 

	$connection = mysqli_connect("localhost","id18828570_surasee","Suwannapal711@gmail.com","id18828570_warehouse_db");
	
	$result = array();
	$result['product'] = array();
	$select= "SELECT *from product";
	$responce = mysqli_query($connection,$select);
	
	while($row = mysqli_fetch_array($responce))
		{
			$index['id']        = $row['0'];
			$index['name']      = $row['1'];
			$index['position']  = $row['2'];
			$index['amount']    = $row['3'];

			
			array_push($result['product'], $index);
		}
			
			$result["success"]="1";
			echo json_encode($result);
			mysqli_close($connection);

 ?>
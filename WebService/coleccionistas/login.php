<?php 

require_once 'mysql_login.php';
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
$conexion = new mysqli(HOSTNAME, USERNAME, PASSWORD, DATABASE);
if ($conexion->connect_error) {
	# code...
    die("Conexion fallida" . $conexion ->connect_error);
}
$user =$_POST['usuario'];
$pass =$_POST['contrasena'];


$sql= "select * from  usuario where usuario_usuario='$user' and usuario_contrasena='$pass'";
$result=$conexion->query($sql);
$datos =array();

if ($result->num_rows >0){

	$datos["estado"]= '1';
	$id=0;
	while ($row= $result->fetch_assoc()) {
		# code...
		$jsonArrayObject = (array('usuario_id'=>$row['usuario_id'],'usuario_nombre'=>$row["usuario_nombre"],'usuario_apellido'=>$row["usuario_apellido"],'usuario_usuario'=>$row["usuario_usuario"],'usuario_contrasena'=>$row["usuario_contrasena"]));
	$datos["Usuarios"][$id]= $jsonArrayObject;
	$id++;
	}
	print json_encode($datos);
}else{
	$datos["estado"]= '2';
	print json_encode($datos);
	$conexion ->close();

}
}
?>

<?php 
require_once 'mysql_login.php';
$conexion = new mysqli(HOSTNAME, USERNAME, PASSWORD, DATABASE);
if ($conexion->connect_error) {
	# code...
    die("Conexion fallida" . $conn ->connect_error);
}
$user =$_REQUEST['usuarioR'];
$pass =$_REQUEST['contrasena'];
$pass2 = $_REQUEST['contrasena2'];
$name=$_REQUEST['nombre'];
$lastname=$_REQUEST['apellido'];
$mail=$_REQUEST['correo'];
header ('Content-type: application/json; charset=utf-8');
mysqli_query($conexion,"SET NAMES 'utf8'");

//////PARA CONSULTAR SI YA HAY UN USUARIO CON EL MISMO USUARIO 
$sql1 = " select * from usuario where usuario_usuario='$user' ";
$result1 = $conexion->query($sql1);

//mysqli_data_seek ($result1,0);
//$extraido= mysqli_fetch_array($result1);

//echo " ".$extraido['usuario_nombre']."</br>";
//echo " ".$extraido['usuario_usuario']."</br>";
//echo "".$result1->num_rows;
if ($result1->num_rows <1){
///////////////////////////////

    if ($pass==$pass2) {
	

        $sql = "insert into usuario (usuario_nombre,usuario_apellido,usuario_usuario,usuario_contrasena,usuario_correo) 
        values('" .	$name ."','" . $lastname . "','" . $user . "','".$pass. "','". $mail ."')";
        #$sql= "select * from  usuario (usuario_nombre,usuario_apellido,usuario_usuario,usuario_contrasena,usuario_correo) VALUES usuario_usuario='$user' and usuario_contrasena='$pass'";

        if ($conexion->query($sql)===true){
            print json_encode(
             array(
                'estado' => '1',
                'mensaje' => 'REGISTRO EXITOSO')
                );
                } 
        else{
                     print json_encode(
                     array(
                        'estado' => '2',
                         'mensaje' => 'Creacion fallida')
                            );
                             }
    }else{
     print json_encode(
         array(
             'estado' => '3',
             'mensaje' => 'CONTRASEÑAS NO COINSIDEN')
                    );
                    }

}else {

    print json_encode(
            array(
                'estado' => '4',
                'mensaje' => 'USUARIO YA EXISTE')
        );
}
$conexion-> close();
?>
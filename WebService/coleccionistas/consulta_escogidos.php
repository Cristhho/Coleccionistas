<?php
require_once 'mysql_login.php';
if($_SERVER['REQUEST_METHOD'] == 'GET'){
$conexion = new mysqli(HOSTNAME, USERNAME, PASSWORD, DATABASE);
if ($conexion->connect_error) {
    die("Connection failed: " . $conexion->connect_error);
}
header ('Content-type: application/json; charset=utf-8');
//mysqli_query($conexion,"SET NAMES 'utf8'");
$conexion->set_charset("utf8");
$sql = "SELECT producto.producto_id,producto.producto_nombre,producto.producto_descripcion,producto.producto_precio,
producto.producto_imagen,producto.producto_categoria,usuario.usuario_correo FROM producto INNER JOIN usuario
ON producto.usuario_id = usuario.usuario_id";
$result = $conexion->query($sql);
if ($result->num_rows > 0){
    $datos["estado"] = 1;
    $index = 0;
    while($row = $result->fetch_assoc()){
        $jsonArrayObject = (array
                            ('id' => $row['producto_id'], 'nombre' => $row["producto_nombre"], 
                             'descripcion' => $row["producto_descripcion"], 'precio' => $row["producto_precio"],
                            'imagen' => $row["producto_imagen"], 'categoria' => $row["producto_categoria"],
                            'correo' => $row["usuario_correo"]));
        $datos["productos"][$index] = $jsonArrayObject;
        $index++;
    }
    print stripslashes_deep(json_encode($datos));
}else{
    print json_encode(
            array(
                'estado' => '2',
                'mensaje' => '0 results')
        );
}
$conexion->close();
}

function stripslashes_deep($value){
    $value = is_array($value) ?
                array_map('stripslashes_deep', $value) :
                stripslashes($value);

    return $value;
}
?>
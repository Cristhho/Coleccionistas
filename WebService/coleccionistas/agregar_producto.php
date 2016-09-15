
<?php 
require_once 'mysql_login.php';
$conexion = new mysqli(HOSTNAME, USERNAME, PASSWORD, DATABASE);
if ($conexion->connect_error) {
	# code...
    die("Conexion fallida" . $conn ->connect_error);
}
$titulo =$_REQUEST['titulo'];
$descripcion =$_REQUEST['descripcion'];
$precio = $_REQUEST['precio'];
$userid=$_REQUEST['userid'];
$categoria = $_REQUEST['categoria'];

header ('Content-type: application/json; charset=utf-8');
mysqli_query($conexion,"SET NAMES 'utf8'");
switch ($categoria) {
    case 'otros':
        $imagen = "/img/otros.png";
       # echo "otros";
        break;
    case 'moneda':
        $imagen = "/img/monedas.png";
        # echo "monedas";
        break;
    case 'videojuegos':
       $imagen = "/img/game.png";
        #echo "viedojuegos ";
        break;
    case 'figuras':
       $imagen = "/img/robot.png";
        #echo "Figuras";
        break;
    case 'comics':
       $imagen = "/img/comics.png";
        #echo "Figuras";
        break;
    case 'pinturas':
       $imagen = "/img/pinturas.png";
        #echo "Figuras";
        break;
    case 'autos':
       $imagen = "/img/autos.png";
        #echo "Figuras";
        break;
    default:
       $imagen = "/img/otros.png";
       # echo "DEFAULT";
        break;
                }

        $sql = "insert into producto (producto_nombre,producto_descripcion,producto_precio,producto_imagen,producto_categoria,usuario_id) 
        values('" .	$titulo ."','" . $descripcion . "'," . $precio . ",'".$imagen. "','". $categoria."',".$userid .")";
        

        if ($conexion->query($sql)===true){
            print json_encode(
             array(
                'estado' => '1',
                'mensaje' => 'PRODUCTO REGISTRADO')
                );
                } 
        else{
                     print json_encode(
                     array(
                        'estado' => '2',
                         'mensaje' => 'ERROR EN EL REGISTRO')
                            );
             }
   


$conexion-> close();
?>
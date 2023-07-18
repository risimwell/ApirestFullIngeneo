export class datosUsuarios {
  // asignar valores a las propiedades de la interfaz
  nombre?: string;
  apellido?: string;
  username?: string;
  password?: string;

  constructor(datos: any) {
    this.nombre = datos.nombre;
    this.apellido = datos.apellido;
    this.username = datos.username;
    this.password = datos.password;
    }


}

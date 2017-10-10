# Packlink Engineering coding test

## Tecnologias

1. Java 8 y SpringBoot 
2. Base de datos documental MongoDB. Embebida (Se contempla Redis)
3. Server Rest, WireMock
4. Gestor de configuración gradle


### Perfiles de configuración

Se realiza un perfil de configuración en formato JSON en repositorio https://github.com/fjgmateu/configuration.git.

El servicio obtendrá la configuración en función del perfil asociado. Esto permite una gestión de configuración en función del entorno dónse se ejecutara

Las tareas gradle se ejecutarán con el perfil como parámetro. Ejemplo  gradle bootRun -Pprofile=test

### Almacenamiento

El almacenamiento lo ha realizado con un mongoDB embebido, ya que se requería que fuera standalone.

En entorno windows 64 funciona correctamente, aunque puede que deje el puerto enganchado cuando se cierre. Si esto ocurre, arrancar el microservicio de nuevo

Se valoró como almacenamiento Redis, por lo que existe un repositorio adaptado a Redis, el cual sería también embebido.

### Servicio mock del Carrier

Se simula el servicio del carrier mediante WireMock.

Para su lanzamiento, en el repositorio https://github.com/fjgmateu/reconciliation-demo.git, en la carpeta ups-server  realizar la siguiente invocación: java -jar wiremock-standalone-2.8.0.jar --port 8056.

En la carpeta mappings se encuentra las respuestas simuladas por el servidor.

### Excepciones

El control de excepciones se realiza con un controlador @ControllerAdvice ReconciliationExceptionHandler. 
Todas las excepciones son capturadas por el controlador, lanzando el correspondiente código HTTP asociada al tipo de excepción producida.


### Extensión a otros carriers.

Para hacer extensible el servicio  a más transportistas, cada carrier tiene un manejador que implementa la interfaz ITrackingHandler, invocando al manejador de cada transportista de manera dinámica con un resolver.

### Funcionamiento

El funcionamiento del servicio que se ha deducido que se pretendía es el siguiente:

1. Llega un evento carrier_sucess, se almacena el shipment con estado 'pending'. (La respuesta de este endpoint es un http code 201 con una cabecera Location:http://localhost/shipment/XXXX para la recuperación mediante un GET por parte del cliente)
2. Se incova al servicio del carrier para obtener el tracking (mock server).
   2.1  Si existe un estado final, y hay coincidencia en peso y número de paquetes, se modifica el shipment a estado 'send_for_conciliation'
3. Se invoca al servicio del carrier, indicando el evento de conciliación en su sistema mediante una petición POST.

Trabajo Práctico Integrador
Programación con Objetos II - 2018 - 2do Semestre
Hito 1
Objetivos
Integrar en la práctica los conocimientos de Diseño y Programación OO estudiados a
lo largo de todo el semestre.
El hito 2 es una extensión a la funcionalidad del enunciado original.
Cronograma
Este trabajo integrador está planificado para ser realizado en dos etapas:
● Hito 1: Se centra en la primera entrega del enunciado y se debe trabajar sobre el
Modelo de Clases, y en la codificación en Java.
● Hito 2: Se centra en una una extensión del enunciado original, y se debe trabajar
sobre la extensión del Modelo de Clases, y en la codificación en Java.
Se establecen tres instancias de visado, en las cuales deberán estar presentes todos los
miembros del grupo para defender el trabajo:
● Primer visado de Hito 1: Presencial en la práctica del Viernes 2 de Noviembre de
2018.
● Primer visado de Hito 2: Presencial en la práctica del Viernes 23 de Noviembre de
2018.
● Segundo visado de Hito 2 (visado final): Presencial en la práctica del Viernes 7 de
Diciembre de 2018.
A la caza de las vinchucas
Introducción
La enfermedad de Chagas, es una enfermedad potencialmente mortal causada por un
parásito llamado Trypanosomo cruzi. Es una enfermedad endémica que se encuentra con
gran presencia en América Latina, incluyendo Argentina. Esta enfermedad se transmite a
los humanos principalmente a través de un insecto llamado Vinchuca.
Los síntomas de esta enfermedad incluyen fiebre, dolor de cabeza, palidez, dolores
musculares, y en fases agudas trastornos cardíacos severos. Con el paso del tiempo la
infección puede causar la muerte súbita o una insuficiencia cardíaca por la destrucción
progresiva del músculo cardíaco .
1
El objetivo de este proyecto es poder realizar un mapeo de la presencia de vinchucas en
el territorio argentino (al menos como comienzo) y así poder encontrar relaciones entre la
presencia de este insecto, los casos de chagas detectados, las organizaciones que se
encuentran en la región, y la posibilidad de minimizar los casos de contagio de la
enfermedad de chagas. Para ello, lo que se convoca es a la participación de las personas
que viven o se encuentran en argentina para que puedan enviar fotos y una serie de
respuestas a un cuestionario para informar de la aparición de este insecto. A esto se lo
llama envío de muestras. Como la participación es de cualquier persona, las muestras
deben poder validarse por otras personas y así cada muestra podrá contar con un nivel de
validación particular.
El proyecto tiene dos aplicaciones conectadas, una móvil donde se realiza la toma de la
información y una web donde se reciben los datos de las muestras y se hace el
procesamiento. Este trabajo se centrará solamente en la lógica de negocio de la aplicación
Web. La aplicación móvil no debe modelar ni tenerla en cuenta, solamente se describirá
para que conozca la interacción.
En este caso, las muestras las toman las personas utilizando una aplicación móvil (QUE
NO DEBE DISEÑAR), la cual fotografia a las vinchucas, indica en qué posición geográfica
se encuentra y envía una serie de respuestas de un cuestionario. Todo esto es recibido por
el sistema Web. Desde allí comienza el trabajo de este trabajo práctico.
Las muestras
Las muestras son el elemento principal de este proyecto. A partir de ellas se realizan casi
todas las evaluaciones y desafíos que requiere el proyecto. En principio, de cada muestra
se debe poder responder cuál es el tipo de vinchuca que han fotografiado, la foto de la
vinchuca, la ubicación y la identificación de la persona que recolectó esa muestra (el alias).
1 Organización mundial de la salud. http://www.who.int/topics/chagas_disease/es/ accedido el 18 de
septiembre de 2018.
Verificación de las muestras
Algo que es importante poder determinar de las muestras es el nivel de verificación que
poseen las mismas. Como las muestras las puede recolectar cualquier persona que posee
la aplicación, las mismas deben chequearse. Existen diferentes maneras para poder saber
si una muestra fue verificada y en qué nivel se encuentra la misma.
La verificación se construye a partir de que varias personas indican que los datos se ven
como veraces. Es decir, por ejemplo que la foto es realmente de una vinchuca y no de otra
cosa. Es por ello que las muestres tienen tres niveles de verificación: baja, media y alta.
En este trabajo, se considera como criterio de verificación detectar si el tipo de vinchuca
que se indicó realmente coincide con el de la foto.
Una muestra estará verificada 3 veces:
1. La persona que recolecto la muestra, envia la foto y los demas datos e indica el tipo
de vinchuca que es.
2. Otra persona visualiza la muestra e indica el tipo de vinchuca que aparece en la foto.
3. Otra persona realiza lo mismo que en el paso 2.
Las alternativas que existen en la verificación son: Vinchuca, Chince Foliada,
Phtia-Chinche, Ninguna, Imagen poco clara.
Se dice que una muestra posee verificación baja cuando la misma está validada por una
única persona y que esta es una persona con conocimientos básicos en el tema. Es el nivel
más básico de participación. La verificación pasará a media si dos personas con nivel de
conocimiento básico confirman la muestra. Y pasará a alta si tres personas confirman la
muestra. Sin embargo, existen personas que poseen un nivel de experto porque han
demostrado que conocen sobre el tema, por ejemplo estudiosos en vinchucas. Si una
muestra está tomada o verificada por uno de estos usuarios, entonces la muestra posee un
nivel alto sin importar el resto de las validaciones.
Es importante notar, que la verificación no significa que la foto realmente sea de una
vinchuca, sino que varias personas se ponen de acuerdo en un veredicto. Por lo cual, puede
verificarse que la foto es poco clara o que en la imagen hay una chinche foliada.
Evolución de conocimiento de los participantes
Los participantes pueden realizar dos actividades básicas. Enviar una muestra y verificar
muestras que envió otra persona. Nunca pueden verificar muestras que hayan enviados
ellos mismos, como así tampoco verificar más de una vez una muestra.
El envío y la verificación de muestras les va dando diferentes niveles en el conocimiento.
El sistema tiene una regla de promociones que funciona de la siguiente manera:
1) Básico: para aquellas personas que recién comienzan a participar. Un participante
nuevo posee nivel básico.
2) Experto: son personas que durante el último mes han realizado más de 10 envíos y
más de 20 revisiones. Tenga en cuenta que este estatus puede cambiar a lo largo
del proyecto y volver al nivel básico.
Finalmente, existen algunos usuarios que poseen conocimiento validado en forma
externa.
Por ejemplo son especialistas reconocidos en la detección de vinchucas. Estos usuarios
siempre son expertos, sin importar el tiempo que llevan participando.
Las organizaciones
También se registran organizaciones no gubernamentales... de las que se puede conocer
donde están ubicadas, el tipo de organización (salud, educativa, cultural, asistencia), y
cuántas personas trabajan allí.
Ubicación
Las ubicaciones están definidas con dos elementos: latitud y longitud. Además de una
ubicación se quiere saber:
1. La distancia entre dos ubicaciones: para esto se pueden consultar alguna de las
siguientes páginas:
a. http://www.mapanet.eu/Resources/Script-Distance.htm (en java script y otros
lenguajes)
b. https://donnierock.com/2015/03/16/calculando-la-distancia-entre-doos-coorde
nadas-en-java/ (en java)
2. Conocer, a partir de una lista de ubicaciones, aquellas que se encuentran a menos
de x metros, o kilómetros.
3. Dado una muestra, conocer todas las muestras obtenidas a menos de x metros o
kilómetros.

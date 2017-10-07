// CAr2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <GL\glut.h>
#include<string.h>
/* ASCII code for the escape key. */
#define ESCAPE 27
int window;
float rtri = 0.0f;
float rquad = 0.0f;
float drive = 0.0f;//-0.37f;
float cloud = 0.0f;
float build = 0.0f;
float blockz = -200.0f;
int glow = 0;
float wheelfloat = 15.0f;
float speedfloat = 0.17f;
void InitGL(int Width, int Height)
{
	glClearColor(0.01f, 0.4f, 1.0f, 0.9f);
	glClearDepth(1.0);
	glDepthFunc(GL_LESS);
	glEnable(GL_DEPTH_TEST);
	glShadeModel(GL_SMOOTH);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0f, (GLfloat)Width / (GLfloat)Height, 0.1f, 100.0f);

	glMatrixMode(GL_MODELVIEW);
}

void ReSizeGLScene(int Width, int Height)
{
	if (Height == 0)
		Height = 1;

	glViewport(0, 0, Width, Height);

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	gluPerspective(45.0f, (GLfloat)Width / (GLfloat)Height, 0.1f, 100.0f);
	glMatrixMode(GL_MODELVIEW);
}

void displayText(float x, float y,float z, int r, int g, int b, const char *string) {
	int j = strlen(string);
	//glLoadIdentity();
	glColor3f(r, g, b);
	glRasterPos3f(x, y,z);
	for (int i = 0; i < j; i++) {
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, string[i]);
	}
}
void drawCloud(float x, float y,float z){
	glColor3f(1.0, 1.0, 1.0);
	glTranslatef(x,y, z);
	glutSolidSphere(0.25f, 20, 20);
	glTranslatef(0.25f, 0.2f, 0.0f);
	glutSolidSphere(0.25f, 20, 20);
	glTranslatef( 0.0f, -0.4f, 0.0f);
	glutSolidSphere(0.25f, 20, 20);
	glTranslatef(0.25f, 0, 0.0f);
	glutSolidSphere(0.25f, 20, 20);
	glTranslatef(0.15f, 0.4f, 0.0f);
	glutSolidSphere(0.25f, 20, 20);
	glTranslatef( 0.2f, -0.22f, 0.0f);
	glutSolidSphere(0.25f, 20, 20);
	
}
void drawBulding(float tx,float ty, float tz){
	glTranslatef(tx, ty, tz);

	glColor3f(0.6f, 0.2f, 0.8f);
	glutSolidCube(0.5);
	glColor3f(0.0f, 0.0f, 0.0f);
	glutWireCube(0.5);
	glTranslatef(0.0f, 0.5f, 0.0f);
	glColor3f(0.6f, 0.2f, 0.8f);
	glutSolidCube(0.5);
	glColor3f(0.0f, 0.0f, 0.0f);
	glutWireCube(0.5);
	

	glTranslatef(0.0f, -0.5f, -0.7f);
	glColor3f(0.6f, 0.2f, 0.2f);
	glutSolidCube(0.5);
	glColor3f(0.0f, 0.0f, 0.0f);
	glutWireCube(0.5);
	
}
void addBlocks(void){
	glBegin(GL_POLYGON);
	glColor3f(0.7, 0.6, 0.4);
	glVertex3f(-0.23f, -0.75f, 1.5f);
	glVertex3f(-0.20f, -0.70f, 1.6f);
	glVertex3f(-0.05f, -0.60f, 1.65f);
	glVertex3f(0.0f, -0.65f, 1.6f);
	glVertex3f(0.04f, -0.60f, 1.8f);
	glVertex3f(0.14f, -0.69f, 1.9f);
	glVertex3f(0.16f, -0.60f, 1.8f);
	glVertex3f(0.23f, -0.72f, 2.3f);
	glVertex3f(0.16f, -0.80f, 2.31f);
	glVertex3f(0.20f, -0.85f, 2.5f);
	glVertex3f(0.15f, -0.90f, 2.8f);
	glVertex3f(0.08f, -0.79f, 2.2f);
	glVertex3f(-0.10f, -0.90f, 3.1f);
	glVertex3f(-0.20f, -0.80f, 2.3f);
	glEnd();
}
void drawCar(float xx,float yy, float zz){
	glTranslatef(xx, yy, zz);
	glBegin(GL_QUADS);
	glColor3f(1.0, 0.0, 0.0);	//back
	glVertex3f(-0.12f, 0.0f, 0.0f);
	glVertex3f(-0.12f, 0.048f, 0.0f);
	glVertex3f(0.12f, 0.048f, 0.0f);
	glVertex3f(0.12f, 0.0f, 0.0f);
	
	glColor3f(1.0, 1.0, 1.0);	//no plate
	glVertex3f(-0.027f, 0.0f, 0.001f);
	glVertex3f(-0.027f, 0.018f, 0.001f);
	glVertex3f(0.027f, 0.018f, 0.001f);
	glVertex3f(0.027f, 0.0f, 0.001f);

	
	glColor3f(0.2, 0.0, 0.0);	//back lambs
	if (glow == 1){
		glColor3f(0.6, 0.0, 0.0);
	}
	glVertex3f(-0.12f, 0.018f, 0.002f);
	glVertex3f(-0.12f, 0.030f, 0.002f);
	glVertex3f(-0.07f, 0.018f, 0.002f);
	glVertex3f(-0.07f, 0.030f, 0.002f);
	glVertex3f(0.12f, 0.018f, 0.002f);
	glVertex3f(0.12f, 0.030f, 0.002f);
	glVertex3f(0.07f, 0.018f, 0.002f);
	glVertex3f(0.07f, 0.03f, 0.002f);

	glColor3f(0.5, 0.0, 0.0);	//diky top
	glVertex3f(-0.12f, 0.048f, 0.0f);
	glVertex3f(-0.12f, 0.06f, -0.048f);
	glVertex3f(0.12f, 0.06f, -0.048f);
	glVertex3f(0.12f, 0.048f, 0.0f);

	glColor3f(0.8, 0.0, 0.0); //back top
	glVertex3f(-0.12f, 0.06f, -0.048f);
	glVertex3f(-0.09f, 0.12f, -0.058f);
	glVertex3f(0.09f, 0.12f, -0.058f);
	glVertex3f(0.12f, 0.06f, -0.048f);

	glColor3f(0.7, 0.7, 0.9); //back window
	glVertex3f(-0.11f, 0.06f, -0.0479f);
	glVertex3f(-0.08f, 0.115f, -0.057f);
	glVertex3f(0.08f, 0.115f, -0.057f);
	glVertex3f(0.11f, 0.06f, -0.0479f);

	glColor3f(0.4, 0.0, 0.0);	//left side
	glVertex3f(-0.12f, 0.0f, 0.0f);
	glVertex3f(-0.12f, 0.06f, 0.0f);
	glVertex3f(-0.12f, 0.06f, -0.21f);
	glVertex3f(-0.12f, 0.0f, -0.21f);

	glColor3f(0.4, 0.0, 0.0);	//right side
	glVertex3f(0.12f, 0.0f, 0.0f);
	glVertex3f(0.12f, 0.06f, 0.0f);
	glVertex3f(0.12f, 0.06f, -0.21f);
	glVertex3f(0.12f, 0.0f, -0.21f);

	glColor3f(0.6, 0.0, 0.0);	//roof
	glVertex3f(-0.09f, 0.12f, -0.058f);
	glVertex3f(-0.09f, 0.12f, -0.128f);
	glVertex3f(0.09f, 0.12f, -0.128f);
	glVertex3f(0.09f, 0.12f, -0.058f);

	glColor3f(0.6, 0.6, 0.6);	//right window
	glVertex3f(0.12f, 0.06f, -0.048f);
	glVertex3f(0.09f, 0.12f, -0.058f);
	glVertex3f(0.09f, 0.12f, -0.128f);
	glVertex3f(0.12f, 0.05f, -0.148f);

	glColor3f(0.6, 0.6, 0.6);	//left window
	glVertex3f(-0.12f, 0.06f, -0.048f);
	glVertex3f(-0.09f, 0.12f, -0.058f);
	glVertex3f(-0.09f, 0.12f, -0.128f);
	glVertex3f(-0.12f, 0.05f, -0.148f);

	glColor3f(0.9, 0.0, 0.0);	//front bonet
	glVertex3f(0.12f, 0.05f, -0.148f);
	glVertex3f(-0.12f, 0.05f, -0.148f);
	glVertex3f(-0.12f, 0.06f, -0.21f);
	glVertex3f(0.12f, 0.06f, -0.21f);
	
	glEnd();

	glLoadIdentity();
	glColor3f(0.25f, 0.25f, 0.25f);
	
	glTranslatef(xx-0.109f, yy+0.0f, zz-0.052f);
	glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
	glRotatef(rquad, 0.0, 0.00, 1.0);
	glutSolidTorus(0.015f, 0.025f, 10, 50);
	glColor3f(0.0f, 0.0f, 0.0f);
	glutWireTorus(0.015f, 0.025f, 10, 50);
	glLoadIdentity();
	glColor3f(0.25f, 0.25f, 0.25f);
	glTranslatef(xx+0.109f, yy+0.0f, zz-0.052f);
	glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
	glRotatef(rquad, 0.0, 0.00, 1.0);
	glutSolidTorus(0.015f, 0.025f, 10, 50);
	glColor3f(0.0f, 0.0f, 0.0f);
	glutWireTorus(0.015f, 0.025f, 10, 50);
}
void moveCar(int key, int x, int y){
	if (key == GLUT_KEY_LEFT && drive > -0.15f){
		drive -= 0.033f;
		glow = 1;
	}
	else if (key == GLUT_KEY_RIGHT && drive < 0.15f){
		drive += 0.033f;
		glow = 1;
	}
	else if (key==GLUT_KEY_UP)
	{
		speedfloat += 0.1f;
		wheelfloat += 5;
		glow = 0;
	}
	else if (key == GLUT_KEY_DOWN){
		glow = 0;
		speedfloat -= 0.1f;
		wheelfloat -= 5;
		if (speedfloat < 0){
			speedfloat = 0;
			wheelfloat = 0;
			glow = 1;
		}
	}
	else
		glow = 0;
}
/* The main drawing function. */
void DrawGLScene()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glLoadIdentity();
	glTranslatef(cloud, 0.0, 0.0);
	drawCloud(-2.8, 1.6,-7.0f);
	drawCloud(3.8, 1.9,-5.0f);

	glLoadIdentity();
	if (blockz > -5.7f && blockz < -4.0f && drive>-0.08f){
		glRotatef(-5, 0.0, 0.5, 1.0);
		displayText(0.006, -0.04, -0.3, 1, 1, 0, "Ayyo!!");
	}
	if (blockz > 42.7f && blockz < 44.0f && drive < 0.08f){
		glRotatef(5, 0.0, 0.5, 1.0);
		displayText(-0.02, -0.04,-0.3, 1, 1, 0, "Amme!!");
	}
	drawCar(drive,-0.3f,-1.1f);

	glLoadIdentity();
	glTranslatef(0.0f, 0.0f, -5.0f);       
	
	glBegin(GL_POLYGON);                // start drawing road
	glColor3f(0.0f, 0.0f, 0.0f);            // Set The Color To Balck
	glVertex3f(-1.0f, -1.0f, 3.0f);
	glVertex3f(-1.0f, 0.0f, -95.0f);
	glVertex3f(1.0f, 0.0f, -95.0f);
	glVertex3f(1.0f, -1.0f, 3.0f);
	glEnd();   

	glBegin(GL_POLYGON);                // start drawing lake
	glColor3f(0.0f, 0.0f, 0.3f);            // Set The Color To Blue
	glVertex3f(1.2f, 0.0f, -95.0f);
	glVertex3f(1.2f, -1.0f, 3.0f);
	glVertex3f(10.2f, -1.0f, 3.0f);
	glVertex3f(100.2f, 0.0f, -95.0f);
	glEnd();
	glBegin(GL_POLYGON);
	glColor3f(0.0f, 0.0f, 0.0f);
	glVertex3f(2.1f, 0.2f, -25.0f);
	glVertex3f(1.6f, 0.2f, -25.0f);
	glVertex3f(1.52f, 0.3f, -25.0f);
	glVertex3f(1.61f, 0.0f, -25.0f);
	glVertex3f(2.6f, 0.0f, -25.0f);
	glVertex3f(2.69f, 0.3f, -25.0f);
	glVertex3f(2.58f, 0.2f, -25.0f);
	glVertex3f(2.1f, 0.2f, -25.0f);
	glVertex3f(2.1f, 0.88f, -25.0f);
	glVertex3f(2.55f, 0.32f, -25.0f);
	glVertex3f(2.1f, 0.2f, -25.0f);
	glEnd();
	glBegin(GL_POLYGON);                // start drawing Ground
	glColor3f(0.0f, 0.3f, 0.0f);            // Set The Color To Green
	glVertex3f(-5.0f, -1.5f, 3.0f);
	glVertex3f(-125.0f, 0.0f, -95.0f);
	glVertex3f(125.0f, 0.0f, -95.0f);
	glVertex3f(5.0f, -1.5f, 3.0f);
	glEnd();
	
	glTranslatef(0.0f, 0.0f, rtri);
	

	glBegin(GL_QUADS);
	glColor3f(1.0, 1.0, 1.0);
	for (float zz = 5.1f,yy=-1.0f; zz > -90.0f; zz -= 4.0f,yy+=0.05f){
		glVertex3f(-0.05f, yy, zz);
		glVertex3f(0.05f, yy, zz);
		glVertex3f(0.05f, yy+0.03f, zz-2.0f);
		glVertex3f(-0.05f, yy+0.03f, zz-2.0f);
	}

	glEnd();
	glLoadIdentity();

	glTranslatef(0.36f, 0.0f, blockz);
	addBlocks();
	glLoadIdentity();
	glTranslatef(-0.36f, 0.0f, blockz-50.0f);
	addBlocks();
	glLoadIdentity();
	glTranslatef(0.0f, 0.0f, build);
	for (float zz = 2.3f,xx=-0.55f,yy=0.0f, i=0; i < 20; zz = -0.9f,xx=0.0f,yy=0.0f,i++){
		
		drawBulding(xx,yy,zz);
	}
	
	
	blockz += speedfloat;
	if (blockz>50)
		blockz = -90.0f;
	cloud += 0.0009f;
	if (cloud > 9)
		cloud = -12.0f;
	rtri += speedfloat;
	build += speedfloat/10; 
	if (rtri>2)
		rtri = -2.0f;
	if (build > 5.9)
		build = -2.1f;
	rquad -= wheelfloat;                    
	glutSwapBuffers();
}

void keyPressed(unsigned char key, int x, int y)
{
	if (key == ESCAPE)
	{
		glutDestroyWindow(window);
		exit(0);
	}
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);

	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_ALPHA | GLUT_DEPTH);

	/* get a 640 x 480 window */
	glutInitWindowSize(640, 480);
	glutInitWindowPosition(50, 100);
	window=glutCreateWindow("Moving Car-karnan");

	glutDisplayFunc(DrawGLScene);
	glutFullScreen();
	glutIdleFunc(DrawGLScene);
	glutReshapeFunc(ReSizeGLScene);
	glutKeyboardFunc(keyPressed);
	glutSpecialFunc(moveCar);
	InitGL(640, 480);
	glutMainLoop();

	return 1;
}


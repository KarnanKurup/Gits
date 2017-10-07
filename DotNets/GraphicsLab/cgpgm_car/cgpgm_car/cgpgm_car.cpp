#include "stdafx.h"
#include <GL\glut.h>


float rspe = 0.0f;


float rang = 0.0f;

void InitGL(int Width, int Height)
{

	glClearColor(0.2f, 0.5f, 0.9f, 0.0f);
	glClearDepth(1.0);
	glDepthFunc(GL_LESS);
	glEnable(GL_DEPTH_TEST);
	glShadeModel(GL_SMOOTH);

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0f, (GLfloat)Width / (GLfloat)Height, 0.1f, 100.0f);

	glMatrixMode(GL_MODELVIEW);
	glOrtho(-15.0, 15.0, -15.0, 15.0, -15.0, 15.0);
}

/* The function called when our window is resized (which shouldn't happen, because we're fullscreen) */
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

float ballX = -0.5f;
float ballY = 0.0f;
float ballZ = 0.0f;
float fltval = 0.005f;
float flang = 0.25f;

void drawBall(float bx) {
	
	glTranslatef(bx, ballY, ballZ);
	glRotatef(-rang, 0.0f, 0.0f, 1.0f);
	glColor3f(0.0, 0.0, 0.0);
	glutWireTorus(0.1, 0.2, 25, 100);
	glColor3f(0.5, 0.5, 0.5);
	glutSolidSphere(0.1, 20, 20);
}


/* The main drawing function. */
void DrawGLScene()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
	glLoadIdentity();

	glTranslatef(0.0f, -1.0f, -6.0f);

	glBegin(GL_QUADS);
	glColor3f(0.3f, 0.3f, 0.3f);

	glVertex3f(-0.87f, 0.5f, 0.0f);
	glVertex3f(-0.58f, 0.78f, 0.0f);
	glVertex3f(-0.38f, 0.78f, 0.0f);
	glVertex3f(-0.38f, 0.5f, 0.0f);

	glVertex3f(-0.01f, 0.78f, 0.0f);
	glVertex3f(0.48f, 0.5f, 0.0f);
	glVertex3f(-0.36f, 0.5f, 0.0f);
	glVertex3f(-0.36f, 0.78f, 0.0f);
	glEnd();

	glBegin(GL_POLYGON);
	glColor3f(1.0f, 0.0f, 0.0f);
	glVertex3f(-1.2f, 0.0f, 0.0f);
	glVertex3f(-1.2f, 0.4f, 0.0f);
	glVertex3f(-0.9f, 0.5f, 0.0f);
	glVertex3f(-0.6f, 0.8f, 0.0f);
	glVertex3f(0.0f, 0.8f, 0.0f);
	glVertex3f(0.5f, 0.53f, 0.0f);
	glVertex3f(1.2f, 0.32f, 0.0f);
	glVertex3f(1.2f, 0.0f, 0.0f);
	glEnd();
	
	

	glBegin(GL_POLYGON);
	glColor3f(0.0, 0.0, 0.0);
	glVertex2f(-5.0, -0.27);
	glVertex2f(-5.0, -0.5);
	glVertex2f(5.0, -0.27);
	glVertex2f(5.0, -0.5);
	glEnd();
	drawBall(-0.75f);
	glLoadIdentity();
	glTranslatef(0.0f, -1.0f, -6.0f);
	drawBall(0.75f);
	glLoadIdentity();
	glTranslatef(-rspe, -1.0f, -8.0f);
	glBegin(GL_TRIANGLES);
	glColor3f(0.0, 1.0, 0.0);
	glVertex2f(-10.0, -0.7);
	glVertex2f(-7.0, 0.6);
	glVertex2f(-4.0, -0.7);
	glVertex2f(-5.0, -0.7);
	glVertex2f(-2.0, 0.6);
	glVertex2f(1.0, -0.7);
	glVertex2f(0.0, -0.7);
	glVertex2f(3.0, 0.6);
	glVertex2f(6.0, -0.7);
	glVertex2f(5.0, -0.7);
	glVertex2f(8.0, 0.6);
	glVertex2f(11.0, -0.7);
	glEnd();

	rspe += fltval;
	if (rspe>5)
		rspe = 0.0f;
	rang -= flang;

	glutSwapBuffers();
}

/* The function called whenever a key is pressed. */
void keyPressed(unsigned char key, int x, int y)
{	
	if (key == 'f' || key == 'F'){
		fltval += 0.005f;
		flang += 0.05f;
	}
	else if (key == 'r' || key == 'R'){
		fltval -= 0.005f;
		flang -= 0.05f;
	}
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);

	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH | GLUT_ALPHA);


	glutInitWindowSize(640, 480);


	glutInitWindowPosition(50, 100);

	/* Open a window */
	glutCreateWindow("My Car");

	InitGL(640, 480);
	glutDisplayFunc(DrawGLScene);
	glutIdleFunc(DrawGLScene);

	glutReshapeFunc(ReSizeGLScene);

	glutKeyboardFunc(keyPressed);
	
	glutMainLoop();

	return 1;
}
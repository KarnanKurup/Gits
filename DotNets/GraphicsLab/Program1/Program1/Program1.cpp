// car.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
// jain.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <GL/glut.h>   
#define ESCAPE 27

/* The number of our GLUT window */
int window;

/* rotation angle for the triangle. */
float rtri = 0.0f;

/* rotation angle for the quadrilateral. */
float rquad = 0.0f;

/* A general OpenGL initialization function.  Sets all of the initial parameters. */
// We call this right after our OpenGL window is created.
void InitGL(int Width, int Height)
{
	// This Will Clear The Background Color To Black
	glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	glEnable(GL_DEPTH_TEST);            // Enables Depth Testing
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();                // Reset The Projection Matrix

	gluPerspective(45.0f, (GLfloat)Width / (GLfloat)Height, 0.1f, 100.0f);

}

/* The function called when our window is resized (which shouldn't happen, because we're fullscreen) */
void ReSizeGLScene(int Width, int Height)
{
	if (Height == 0)                // Prevent A Divide By Zero If The Window Is Too Small
		Height = 1;

	glViewport(0, 0, Width, Height);        // Reset The Current Viewport And Perspective Transformation

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	gluPerspective(45.0f, (GLfloat)Width / (GLfloat)Height, 0.1f, 100.0f);
	glMatrixMode(GL_MODELVIEW);
}

float ballX = -0.5f;
float ballY = 0.0f;
float ballZ = 0.0f;

void drawBall(void) {
	glLoadIdentity();                // Reset The View
	glColor3f(0.0, 0.0, 0.0); //set ball colour
	glTranslatef(-rtri+ballX, ballY, -6.0f);
	glRotatef(rquad, 0, 0, 1);
	glColor3f(0.4, 0.4, 0.4);
	glutSolidTorus(0.1, 0.3, 10, 50);
	glColor3f(0.0, 0.0, 0.0);
	glutWireTorus(0.1, 0.3, 10, 50);
	glLoadIdentity();
	glTranslatef(-rtri+0.95, ballY, -6); //moving it toward the screen a bit on creation
	glRotatef(rquad, 0, 0, 1);
	glColor3f(0.4, 0.4, 0.4);
	glutSolidTorus(0.1, 0.3, 10, 50);
	glColor3f(0.0, 0.0, 0.0);
	glutWireTorus(0.1, 0.3, 10, 50); //
}


/* The main drawing function. */
void DrawGLScene()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);        // Clear The Screen And The Depth Buffer
	glLoadIdentity();                // Reset The View
	glBegin(GL_QUADS);                // start drawing a polygon
	glColor3f(0,0,0);

	glVertex3f(-5.0f, -0.5, -6.0);
	glVertex3f(-5, -0.3, -6.01f);
	glVertex3f(5, -0.3, -6.01f);
	glVertex3f(5, -0.5, -6.0);
	glEnd();
	glTranslatef(-rtri, 0.0f, -6.0f); 

	glBegin(GL_POLYGON);                // start drawing a polygon
	glColor3f(0.82f, 0.82f, 0.30f);

	glVertex3f(-0.2f, 0.5f, 0.0f);
	glVertex3f(-0.3f, 0.8f, 0.0f);
	glVertex3f(-0.5f, 0.45f, 0.0f);
	glVertex3f(0, 0.3f, 0.0f);
	glVertex3f(0, -0.2f, 0.0f);
	glVertex3f(0.5f, -0.2f, 0.0f);
	glVertex3f(0.6f, 0.35f, 0.0f);
	glVertex3f(1.6f, 0.6f, 0.0f);
	glVertex3f(0.5f, 0.5f, 0.0f);
	glVertex3f(0.3f, 0.7f, 0.0f);
	glVertex3f(-0.05f, 0.7f, 0.0f);
	glEnd();
	glBegin(GL_POLYGON);                // start drawing a polygon
	glColor3f(0.82f, 0.40f, 0.30f);

	glVertex3f(0.5f, 0.5f, 0.0f);
	glVertex3f(0.3f, 0.7f, 0.0f);
	glVertex3f(0.2f, 1.1f, 0.0f);
	glVertex3f(0.55f, 1.2f, 0.0f);
	glVertex3f(0.75f, 0.55f, 0.0f);
	glEnd();
	glBegin(GL_POLYGON);                // start drawing a polygon
	glColor3f(0.82f, 0.40f, 0.30f);

	glVertex3f(0.3f, 0.7f, 0.03f);
	glVertex3f(0.089f, 0.295f, 0.03f);
	glVertex3f(0.2f, -0.19f, 0.03f);
	glVertex3f(0.35f, -0.19f, 0.03f);
	glVertex3f(0.1f, 0.28f, 0.03f);
	glVertex3f(0.75f, 0.55f, 0.03f);
	glEnd();
	glBegin(GL_QUADS);                // start drawing a polygon
	glColor3f(0.82f, 0.40f, 0.30f);

	glVertex3f(0.3f, 1.1f, 0.01f);
	glVertex3f(0.35f, 0.9f, 0.01f);
	glVertex3f(-0.2f, 0.6f, 0.01f);
	glVertex3f(-0.2f, 0.65f, 0.01f);
	glEnd();
	

	glTranslatef(0.3, 1.3, 0);
	glutSolidSphere(0.2, 100, 10);
	glBegin(GL_POLYGON);                // start drawing a polygon
	glColor3f(0.1f, 0.1f, 0.3f);

	glVertex3f(-0.56, -0.7f, 0.05f);
	glVertex3f(-0.45, -0.7f, 0.05f);
	glVertex3f(-0.45, -0.6f, -0.05f);
	glVertex3f(-0.56, -0.6f, -0.05f);
	glEnd();
	drawBall();

	rtri += 0.002f;
	if (rtri>6.5)
		rtri = -6.0f;
	rquad -= 4.0f;
	glutSwapBuffers();
}

/* The function called whenever a key is pressed. */
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
	window = glutCreateWindow("Moving Car");
	glutDisplayFunc(&DrawGLScene);
	glutIdleFunc(&DrawGLScene);
	glutReshapeFunc(&ReSizeGLScene);
	glutKeyboardFunc(&keyPressed);
	InitGL(640, 480);
	glutMainLoop();

	return 1;
}







// MCA22_3DSahpes.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<GL\glut.h>
float ang = 0.0;
float trans = 0.0;
void my3d(void){
	glClearColor(1.0, 1.0, 1.0, 1.0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-5.0, 5.0, -5.0, 5.0, -5.0, 5.0);
	glMatrixMode(GL_MODELVIEW);

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glLoadIdentity();
	glColor3f(1.0, 0.0, 0.0);
	glScalef(trans, trans, 0.0);
	glRotatef(ang, 0.6, 0.9, 0.3);
	glutWireCone(0.9, 2.0, 60, 100);

	glLoadIdentity();
	glColor3f(0.0, 0.4, 0.0);
	glTranslatef(2.5, trans, 3.0);
	glRotatef(ang, 0.5, 1.0, 0.3);
	glutSolidCube(1.7);

	glLoadIdentity();
	glColor3f(0.0, 0.75, 1.0);

	glTranslatef(-trans, trans, 3.0);
	glRotatef(ang, -0.5, 1.0, 0.3);
	glutWireSphere(0.75, 30, 60);

	glutSwapBuffers();
}
int s = 0;
void refresh(int v){
	ang += 1.0;
	if (trans<5.0 && s == 0){
		trans += 0.01;
		s = 0;
	}
	else {
		s = 1;
		trans -= 0.01;
		if (trans <= -5.0)
			s = 0;
	}
	glutTimerFunc(10, refresh, 0);
}
void key(unsigned char c, int x, int y){
	if (c == 's'){
		glutTimerFunc(10, refresh, 0);
	}
}
int main(int argc, char* argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glutInitWindowSize(700, 700);
	glutInitWindowPosition(50, 100);
	glutCreateWindow("MCA22_3DSahpes");
	glClearColor(0.0, 0.0, 0.0, 0.0);
	glutDisplayFunc(my3d);
	glutKeyboardFunc(key);
	glutIdleFunc(my3d);
	glutMainLoop();
	return 0;
}


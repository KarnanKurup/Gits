// Cgpgm_Mpointer.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<GL/glut.h>
int px1 = 0, py1 = 0, px2 = 0, py2 = 0;
void drawp(void){
	
	
}
void display(void){
	
	glClear(GL_COLOR_BUFFER_BIT);
	glBegin(GL_LINES);
	glVertex2i(px1, py1);
	glVertex2d(px2, py2);
	glEnd();
	glFlush();
	glutSwapBuffers();
	
}
void mouse(int b, int s, int x, int y){
	printf_s("%d-%d\n", x, y);
	if (b == GLUT_LEFT_BUTTON && s == GLUT_DOWN)
	{
		px1 = x; py1 = 600 - y;
	}
	else if (b == GLUT_LEFT_BUTTON && s == GLUT_UP)
	{
		px2 = x; py2 = 600 - y;
		display();
	}
	
}
void init(void){
	glClearColor(0.0, 0.0, 0.0, 0.0);
	glColor3f(1.0, 1.0, 1.0);
	glPointSize(5.0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0, 600, 0, 600);
}
int main(int argc, char* argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutInitWindowSize(600, 600);
	glutInitWindowPosition(50, 100);
	glutCreateWindow("MouseDraw");
	init();
	glutDisplayFunc(display);
	glutMouseFunc(mouse);
	//glutIdleFunc(drawp);
	glutMainLoop();
	return 0;
}


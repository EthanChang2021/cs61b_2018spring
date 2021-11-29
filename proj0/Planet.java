import java.util.*;

public class Planet {
	public static final double G = 6.67e-11;

	public double xxPos;		//current x position
	public double yyPos;		//current y position
	public double xxVel;		//current velocity in the x direction
	public double yyVel;		//current velocity in the y direction
	public double mass;     	//its mass
	public String imgFileName;	


	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public Planet(){
		this.xxPos = 0;
		this.yyPos = 0;
		this.xxVel = 0;
		this.yyVel = 0;
		this.mass = 0;
		this.imgFileName = null;
	}
	/**
		returns the distance between the supplied planet and the planet that is doing the calculation.
	*/

	public double calcDistance(Planet p) {
		double dx = (p.xxPos - this.xxPos);
		double dy = (p.yyPos - this.yyPos);
		double distance = Math.pow((dx * dx + dy * dy), 0.5);
		return distance;
	}

	public double calcForceExertedBy(Planet p) {
		double Force = (G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
		return Force;
	}

	public double calcForceExertedByX(Planet p) {
		double Fx = this.calcForceExertedBy(p) * ((p.xxPos - this.xxPos) / this.calcDistance(p));
		return Fx;
	}

	public double calcForceExertedByY(Planet p) {
		double Fy = this.calcForceExertedBy(p) * ((p.yyPos - this.yyPos) / this.calcDistance(p));
		return Fy;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double Fnetx = 0;;

		for(int i = 0; i < allPlanets.length; i++){
			if(this.equals(allPlanets[i])){
				continue;
			}
			else{
				Fnetx += this.calcForceExertedByX(allPlanets[i]);
			}
		} 
		return Fnetx;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double Fnety = 0;

		for(int j = 0; j < allPlanets.length; j++){
			if(this.equals(allPlanets[j])){
				continue;
			}
			else{
				Fnety += this.calcForceExertedByY(allPlanets[j]);
			}
		}
		return Fnety;
	}


	public void update(double dt, double fX, double fY){
		double a_netx = fX / this.mass;
		double a_nety = fY / this.mass;
		xxVel += dt * a_netx;
		yyVel += dt * a_nety; 

		xxPos += dt * xxVel;
		yyPos += dt * yyVel; 

	}
//Draw one Planet.
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "/images/" + this.imgFileName);
	}

	




}
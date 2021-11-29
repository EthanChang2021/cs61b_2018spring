public class NBody{
	public static double readRadius(String filePath){
		In in = new In(filePath);
		int numberOfPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filePath){
		In in = new In(filePath);
		int numberOfPlanets = in.readInt();
		in.readDouble();

		Planet[] actualOutput = new Planet[numberOfPlanets];

		for(int i = 0; i < numberOfPlanets; i++){
			actualOutput[i] = new Planet();
		}

		for(int j = 0; j < numberOfPlanets; j++){
			actualOutput[j].xxPos = in.readDouble();
			actualOutput[j].yyPos = in.readDouble();
			actualOutput[j].xxVel = in.readDouble();
			actualOutput[j].yyVel = in.readDouble();
			actualOutput[j].mass = in.readDouble();
			actualOutput[j].imgFileName = in.readString();

		}
		return actualOutput;
	}

	public static void main(String[] args){

		StdDraw.enableDoubleBuffering(); //to prevent flickering in the animation.
		
		double time = 0;
		double T = Double.parseDouble(args[0]); 
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		/*
		this is to read the radius of the universe and the read in the planets.
		*/
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		String background_Path = "/Users/zhangyuxin/Documents/Stevens Institute of Technology(Computer Science)/CS61B(2018spring)/cs61b_2018spring/proj0/images/starfield.jpg";
		
		//Draw the background starfield.
		StdDraw.clear();
		StdDraw.setScale(-radius, radius);

		while(time < T){
			double xForces[] = new double[planets.length];
			double yForces[] = new double[planets.length];

			for(int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, background_Path); 

				// draw the planets.
			for(Planet p : planets){
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

			StdDraw.clear();

			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);

		for(int i = 0; i < planets.length; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}

}


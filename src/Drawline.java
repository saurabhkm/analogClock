
import java.awt.*;
import java.applet.*;
import java.lang.Math;

//<applet code="DrawLine.class" height=1000 width=1000/> >

public class Drawline extends Applet implements Runnable {

	int height, width;

	Thread t = null;
	int state;
	boolean stopFlag;

	int sec = 0;
	int s_l = 100;
	int s_deg = -90;
	double s_rad = 0;
	double[] s_cont = new double[2];

	int min = 0;
	int m_l = 80;
	int m_deg = -90;
	double m_rad = 0;
	double[] m_cont = new double[2];

	int hrs = 0;
	int h_l = 50;
	int h_deg = -90;
	double h_rad = 0;
	double[] h_cont = new double[2];

	int a = 300, b = 300, s_c = a, s_d = b - s_l;
	int m_c = a, m_d = b - m_l;
	int h_c = a, h_d = b - h_l;

	public void init() {
		setBackground(Color.black);
		// setForeground(Color.red);
	}

	public void start() {
		t = new Thread(this);
		stopFlag = false;
		t.start();
	}

	public void run() {
		/*
		 * for(;;){ try{ t.sleep(1000); }catch(InterruptedException e){}
		 * repaint(); }
		 */

		try {
			for (;;) {
				if (sec < 61) {
					t.sleep(250);
					incSec();
				} else {
					sec = 0;
					if (min < 61) {
						incMin();
					} else {
						min = 0;
						if (hrs < 24) {
							incHrs();
						} else {
							sec = 0;
							min = 0;
							hrs = 0;
						}
					}
				}
				System.out.println(hrs + ":" + min + ":" + sec);
				repaint();
			}
		} catch (Exception e) {
		}
		// repaint();
	}

	public void stop() {
		stopFlag = true;
		t = null;
	}

	public void paint(Graphics g) {

		g.setColor(Color.green);

		g.drawRect(190, 190, 220, 220);

		// s_rad=Math.toRadians(s_deg);

		/*
		 * s_cont=calcCords(a,b,s_rad,s_l);
		 * 
		 * s_c=(int)s_cont[0]; s_d=(int)s_cont[1];
		 */

		g.drawLine(a, b, s_c, s_d);
		g.drawLine(a, b, m_c, m_d);
		g.drawLine(a, b, h_c, h_d);
		// s_deg+=6;

		g.setColor(Color.red);
		g.drawString("12", 300, 210);
		g.drawString("6", 300, 400);
		g.drawString("9", 200, 300);
		g.drawString("3", 390, 300);
	}

	public double[] calcCords(int ai, int bi, double theta, int len) {
		double[] cords = new double[2];

		double x = len * Math.cos(theta);
		double y = len * Math.sin(theta);

		cords[0] = ai + x;
		cords[1] = bi + y;

		return cords;

	}

	void incSec() {
		sec++;
		s_deg += 6;

		s_rad = Math.toRadians(s_deg);
		s_cont = calcCords(a, b, s_rad, s_l);
		s_c = (int) s_cont[0];
		s_d = (int) s_cont[1];

		// sec++;
		// s_deg+=6;
	}

	void incMin() {
		min++;
		m_deg += 6;

		m_rad = Math.toRadians(m_deg);
		m_cont = calcCords(a, b, m_rad, m_l);
		m_c = (int) m_cont[0];
		m_d = (int) m_cont[1];

		// min++;
		// m_deg+=6;
	}

	void incHrs() {
		hrs++;
		h_deg += 6;

		h_rad = Math.toRadians(h_deg);
		h_cont = calcCords(a, b, h_rad, h_l);
		h_c = (int) h_cont[0];
		h_d = (int) h_cont[1];

		// hrs++;
		// h_deg+=6;
	}

}

package gui.util.export;

import estadistica.regresion.RegresionLineal;

public class SVGExport {

    public static String generarSVG(double[] x, double[] y, RegresionLineal r) {

        double W = 760;
        double H = 340;

        double PAD_TOP = 20;
        double PAD_RIGHT = 30;
        double PAD_BOTTOM = 40;
        double PAD_LEFT = 50;

        double plotW = W - PAD_LEFT - PAD_RIGHT;
        double plotH = H - PAD_TOP - PAD_BOTTOM;

        double minX = Double.MAX_VALUE, maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE, maxY = -Double.MAX_VALUE;

        for (double v : x) { minX = Math.min(minX, v); maxX = Math.max(maxX, v); }
        for (double v : y) { minY = Math.min(minY, v); maxY = Math.max(maxY, v); }

        double rangeX = (maxX - minX == 0) ? 1 : (maxX - minX);
        double rangeY = (maxY - minY == 0) ? 1 : (maxY - minY);

        double padX = rangeX * 0.12;
        double padY = rangeY * 0.12;

        double domMinX = minX - padX;
        double domMaxX = maxX + padX;
        double domMinY = minY - padY;
        double domMaxY = maxY + padY;

        java.util.function.DoubleFunction<Double> sx =
                v -> PAD_LEFT + ((v - domMinX) / (domMaxX - domMinX)) * plotW;

        java.util.function.DoubleFunction<Double> sy =
                v -> PAD_TOP + plotH - ((v - domMinY) / (domMaxY - domMinY)) * plotH;

        double rx1 = domMinX;
        double ry1 = r.getIntercepto() + r.getPendiente() * rx1;

        double rx2 = domMaxX;
        double ry2 = r.getIntercepto() + r.getPendiente() * rx2;

        StringBuilder svg = new StringBuilder();

        svg.append("<svg viewBox='0 0 ").append(W).append(" ").append(H)
           .append("' xmlns='http://www.w3.org/2000/svg'>");

        // Fondo 
        svg.append("<rect width='100%' height='100%' fill='#12121f'/>");

        // GRID 
        int grid = 5;

        for (int i = 0; i <= grid; i++) {
            double t = (double) i / grid;

            // ================= X GRID =================
            double vx = domMinX + t * (domMaxX - domMinX);
            double gx = sx.apply(vx);

            svg.append("<line x1='").append(gx)
               .append("' y1='").append(PAD_TOP)
               .append("' x2='").append(gx)
               .append("' y2='").append(PAD_TOP + plotH)
               .append("' stroke='#2a2a40' stroke-width='1'/>");

            svg.append("<text x='").append(gx)
               .append("' y='").append(PAD_TOP + plotH + 16)
               .append("' text-anchor='middle' fill='#555' font-size='11'>")
               .append(String.format("%.1f", vx))
               .append("</text>");

            // ================= Y GRID =================
            double vy = domMinY + t * (domMaxY - domMinY);
            double gy = sy.apply(vy);

            svg.append("<line x1='").append(PAD_LEFT)
               .append("' y1='").append(gy)
               .append("' x2='").append(PAD_LEFT + plotW)
               .append("' y2='").append(gy)
               .append("' stroke='#2a2a40' stroke-width='1'/>");

            svg.append("<text x='").append(PAD_LEFT - 8)
               .append("' y='").append(gy + 4)
               .append("' text-anchor='end' fill='#555' font-size='11'>")
               .append(String.format("%.1f", vy))
               .append("</text>");
        }

        // Ejes
        svg.append("<line x1='").append(PAD_LEFT)
           .append("' y1='").append(PAD_TOP)
           .append("' x2='").append(PAD_LEFT)
           .append("' y2='").append(PAD_TOP + plotH)
           .append("' stroke='#333' stroke-width='1.5'/>");

        svg.append("<line x1='").append(PAD_LEFT)
           .append("' y1='").append(PAD_TOP + plotH)
           .append("' x2='").append(PAD_LEFT + plotW)
           .append("' y2='").append(PAD_TOP + plotH)
           .append("' stroke='#333' stroke-width='1.5'/>");

        // Recta
        svg.append("<line x1='").append(sx.apply(rx1))
           .append("' y1='").append(sy.apply(ry1))
           .append("' x2='").append(sx.apply(rx2))
           .append("' y2='").append(sy.apply(ry2))
           .append("' stroke='#6fa8ff' stroke-width='2' stroke-dasharray='6,3'/>");

        // Puntos
        for (int i = 0; i < x.length; i++) {
            svg.append("<circle cx='").append(sx.apply(x[i]))
               .append("' cy='").append(sy.apply(y[i]))
               .append("' r='5' fill='#a699d1' opacity='0.9'/>");
        }

        // Labels simples
        svg.append("<text x='55' y='15' fill='#aaa' font-size='11'>• Datos </text>");
        svg.append("<line x1='100' y1='10' x2='120' y2='10' stroke='#6fa8ff' stroke-width='2' stroke-dasharray='4,2'/>");
        svg.append("<text x='125' y='15' fill='#aaa' font-size='11'> Recta</text>");

        svg.append("</svg>");

        return svg.toString();
    }
}
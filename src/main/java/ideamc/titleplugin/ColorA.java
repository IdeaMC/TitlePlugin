package ideamc.titleplugin;

import java.awt.Color;

public class ColorA {
    public static String ColorB(String args) {

        StringBuilder title = new StringBuilder();

        if(args.contains("&#")){
            // 找到第一个颜色字符序列的位置
            int startTagPos = args.indexOf("&#");
            // 找到最后一个颜色字符序列的位置
            int endTagPos = args.lastIndexOf("&#");
            // 起始颜色
            String colorStart = args.substring(startTagPos+1, startTagPos+8);
            if(!colorStart.substring(1,7).matches("^[0-9a-fA-F]{6}$")){
                colorStart = "#FFFFFF";
            }
            // 结束颜色
            String colorEnd = args.substring(endTagPos+1, endTagPos+8);
            if(!colorEnd.substring(1,7).matches("^[0-9a-fA-F]{6}$")){
                colorEnd = "#FFFFFF";
            }
            // 文本内容
            String content = args.substring(startTagPos+8, endTagPos);
            // 渐变步数
            int steps = content.length();

            if(startTagPos != 0){
                title.append(args, 0, startTagPos);
            }

            for (int i = 0; i < steps; i++) {
                float ratio = (float) i / (float) steps;
                Color color = interpolateColor(colorStart, colorEnd, ratio);
                String hex = Integer.toHexString(color.getRGB()).substring(2); // 将颜色转换为十六进制格式
                title.append("§x");
                for (int j = 0; j < hex.length(); j++) {
                    title.append("§").append(hex.charAt(j));
                }
                title.append(content.charAt(i));
            }

            if(endTagPos+8 != args.length()){
                title.append(args, endTagPos+8, args.length());
            }
        }else if(args.contains("&")){
            String newstring = args.replace("&", "§");
            title.append(newstring);
        }

        return title.toString();
    }

    private static Color interpolateColor(String colorStart, String colorEnd, float ratio) {
        Color c1 = Color.decode(colorStart);
        Color c2 = Color.decode(colorEnd);

        int red = (int) (c1.getRed() + ratio * (c2.getRed() - c1.getRed()));
        int green = (int) (c1.getGreen() + ratio * (c2.getGreen() - c1.getGreen()));
        int blue = (int) (c1.getBlue() + ratio * (c2.getBlue() - c1.getBlue()));

        return new Color(red, green, blue);
    }
}

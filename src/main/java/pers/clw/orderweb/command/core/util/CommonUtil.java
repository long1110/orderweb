package pers.clw.orderweb.command.core.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * 描述: 通用工具类
 *
 * @version V1.0
 * @author：xujianjun
 * @date：2016年11月9日 上午9:37:47
 */
public class CommonUtil {

    /**
     * 将map请求参数转换成数据库查询条件 如 a=1 and b=2 and c=3
     *
     * @param parms
     * @return
     */
    public static String changeMapToWhereCondition(Map<String, String> parms) {
        if (parms == null || parms.isEmpty()) {
            return " 1=1 ";
        }
        StringBuilder sb = new StringBuilder();
        for (String key : parms.keySet()) {
            if ("page".equals(key) || "rows".equals(key) || "sort".equals(key) || "order".equals(key)) {
                continue;
            }
            if (StringUtils.isNotBlank(parms.get(key))) {
                if (sb.toString().length() > 0) {
                    sb.append(" and ").append(key).append("='").append(parms.get(key)).append("'");
                } else {
                    sb.append(key).append("='").append(parms.get(key)).append("'");

                }
            }
        }
        if (sb.length() == 0) {
            return "1=1";
        }
        return sb.toString();
    }

    /**
     * 将对象转换成json对象
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
/*	public static JSONObject objectToJsonObj(Object object) throws JsonProcessingException {
        return JSONObject.fromObject(new ObjectMapper().writeValueAsString(object).replaceAll("null", "\"\""));
	}*/

    /**
     * md5加密
     *
     * @param pw
     * @return
     */
    public static String MD5(String pw) {
        try {

            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = pw.getBytes();
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String byteArrayToHex(byte[] byteArray) {

        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

    /**
     * 不可通用
     *
     * @param request
     * @param filePath
     */
    public static void deleteFile(HttpServletRequest request, String filePath) {
        String path = request.getServletContext().getRealPath("/");
        File temp = new File(path);
        path = temp.getParent();
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        File file2 = new File(path + "/upload/" + fileName);
        file2.deleteOnExit();
        ;
    }

    /**
     * 不可通用
     *
     * @param file
     * @param request
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws Exception, IOException {
        String path = request.getServletContext().getRealPath("/");
        File temp = new File(path);
        path = temp.getParent();
        File file2 = new File(path + "/upload/");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String fileName = System.currentTimeMillis()
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件
        File file3 = new File(file2, fileName);
        file.transferTo(file3);
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        url = url.replace(uri, "/upload/" + fileName);
        return url;
    }

    public static String generateOrderNo() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return "T" + sDateFormat.format(new Date());
    }

    public static String generateTradeNo() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		String code="";
//		for (int i = 0; i < 6; i++) {
//			code += (int) (Math.random() * 10) + "";
//		}
//		System.out.println(generateTradeNo());
        return sDateFormat.format(new Date()) + new DecimalFormat("000000").format(Math.random() * 1000000);
    }


//	/**
//	 * 获取session中的用户信息
//	 *
//	 * @param request
//	 * @return
//	 */
	/*public static User getSessinoUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			user = new User();
			user.setId(1);
			user.setName("测试");
			user.setUserName("test");
		}
		return user;
	}*/

    public static void outPutImage(HttpServletResponse response, InputStream inputStream) {

        if (inputStream == null) {
            return;
        }
        OutputStream outputStream = null;
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/png");
            outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            for (int len = -1; (len = inputStream.read(b)) != -1; ) {
                outputStream.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 编码生成
     *
     * @param prefix
     * @param num
     * @return
     */
    public static String generateCode(String prefix, long num) {
        DecimalFormat decimalFormat = new DecimalFormat("0000000000");
        return prefix + decimalFormat.format(num);
    }

    /**
     * @param begin  格式HH:mm
     * @param end    格式HH:mm
     * @param reduce 减少的分钟数
     * @return
     * @throws Exception
     */
    public static long getMinutes(String begin, String end, long reduce) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH:mm");
        String tempDate = "20101010";
        long b = simpleDateFormat.parse(tempDate + begin).getTime();
        long e = simpleDateFormat.parse(tempDate + end).getTime();
        // long breaks=data.getLunchBreak()*60000;//午休时间
        // long hour=(e-b-reduce)/(1000*60*60);
        long min = (e - b - reduce * 60000) / 60000;
        return min;
        // String workTime=hour+"";
        // if(min>=30){
        // workTime=workTime+".5";
        // }
        // data.setWorkTime(workTime);
        // min=CommonUtil.getMinutes(begin,end);
    }

	/*public static Member getMember(HttpServletRequest request) {
		return (Member) request.getAttribute("member");
	}
*/

    /**
     * 计算用时
     *
     * @param start
     * @param end
     * @param unit
     * @return

    public static long timekeep(Date start, Date end, String unit) {
        long difference = end.getTime() - start.getTime();
        long u ;
        long r ;
        //相差天数：
        if (Constants.ORDER_UNIT_D.equals(unit)) {
            u = (difference/(3600 * 24))%1000;
            r = difference / (3600 * 24 * 1000);
        } else if (Constants.ORDER_UNIT_H.equals(unit)) {
            //相差小时：
            u = (difference/3600)%1000;
            r = difference / (3600 * 1000);
        } else if (Constants.ORDER_UNIT_M.equals(unit)) {
            //相差分钟：
            u = (difference/60)%1000;
            r = difference / (60 * 1000);
        } else if (Constants.ORDER_UNIT_S.equals(unit)) {
            u = difference%1000;
            r = difference/000;
            return difference / (1000) + 1;
        } else {//默认按小时运算
            u = (difference/3600)%1000;
            r = difference / (3600 * 1000);
        }
        if(u>0||r==0){
            return r + 1;
        }else {
            return r;
        }

    }

    public static Date timeTask(Date start, String unit,int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(start);
        //相差天数：
        if (Constants.ORDER_UNIT_D.equals(unit)) {
            calendar.add(Calendar.DAY_OF_YEAR,num);
        } else if (Constants.ORDER_UNIT_H.equals(unit)) {//相差小时：
            calendar.add(Calendar.HOUR,num);
        } else if (Constants.ORDER_UNIT_M.equals(unit)) {//相差分钟：
            calendar.add(Calendar.MINUTE,num);
        } else if (Constants.ORDER_UNIT_S.equals(unit)) {
            calendar.add(Calendar.SECOND,num);
        }
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date s = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(s);
        calendar.add(Calendar.HOUR,1);
        calendar.add(Calendar.MINUTE,20);
        calendar.add(Calendar.SECOND,10);
        Date e = calendar.getTime();
        System.out.println(s);
        System.out.println(e);
        System.out.println(timekeep(s,e,"2"));
    }

    public static String cutCenterImage(MultipartFile files , String src, int w, int h) throws IOException{
        try{
            CommonsMultipartFile cf = (CommonsMultipartFile)files;
            //这个myfile是MultipartFile的
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File file = fi.getStoreLocation();
            Image img = ImageIO.read(file);
            int width = img.getWidth(null);
            int height = img.getHeight(null);
            if((width*1.0)/w < (height*1.0)/h){
                if(width > w){
                    h = Integer.parseInt(new DecimalFormat("0").format(height * w/(width*1.0)));
                    // log.debug("change image's height, width:{}, height:{}.",w,h);
                }
            } else {
                if(height > h){
                    w = Integer.parseInt(new DecimalFormat("0").format(width * h/(height*1.0)));
                    //log.debug("change image's width, width:{}, height:{}.",w,h);
                }
            }
            BufferedImage bis = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = bis.getGraphics();
            g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
            g.dispose();

            ImageIO.write(bis, "jpg", new File(src +".jpg"));
        }catch(Exception e){

        }
        return "";
    }
     */
    /**
     * 获取验证码
     * @return
     */
    public static String getCode(){
        Random rad=new Random();
        String result  = rad.nextInt(10000) +"";
        if(result.length()!=4){
            return getCode();
        }
        return result;
    }
}

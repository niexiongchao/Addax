/*
 *
 *  * Licensed to the Apache Software Foundation (ASF) under one
 *  * or more contributor license agreements.  See the NOTICE file
 *  * distributed with this work for additional information
 *  * regarding copyright ownership.  The ASF licenses this file
 *  * to you under the Apache License, Version 2.0 (the
 *  * "License"); you may not use this file except in compliance
 *  * with the License.  You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied.  See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 *
 */

package com.wgzhao.addax.plugin.reader.datareader.util;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

/**
 * generate a faker address
 */
public class AddressUtil
{
    private static final String[] COUNTRIES = {
            "阿富汗", "阿拉斯加", "阿尔巴尼亚", "阿尔及利亚", "安道尔", "安哥拉", "安圭拉岛英", "安提瓜和巴布达",
            "阿根廷", "亚美尼亚", "阿鲁巴岛", "阿森松", "澳大利亚", "奥地利", "阿塞拜疆", "巴林", "孟加拉国",
            "巴巴多斯", "白俄罗斯", "比利时", "伯利兹", "贝宁", "百慕大群岛", "不丹", "玻利维亚", "波斯尼亚和黑塞哥维那",
            "博茨瓦纳", "巴西", "保加利亚", "布基纳法索", "布隆迪", "喀麦隆", "加拿大", "加那利群岛", "佛得角",
            "开曼群岛", "中非", "乍得", "智利", "圣诞岛", "科科斯岛", "哥伦比亚", "巴哈马国", "多米尼克国", "科摩罗",
            "刚果", "科克群岛", "哥斯达黎加", "克罗地亚", "古巴", "塞浦路斯", "捷克", "丹麦", "迪戈加西亚岛", "吉布提",
            "多米尼加共和国", "厄瓜多尔", "埃及", "萨尔瓦多", "赤道几内亚", "厄立特里亚", "爱沙尼亚", "埃塞俄比亚", "福克兰群岛",
            "法罗群岛", "斐济", "芬兰", "法国", "法属圭亚那", "法属波里尼西亚", "加蓬", "冈比亚", "格鲁吉亚", "德国", "加纳",
            "直布罗陀", "希腊", "格陵兰岛", "格林纳达", "瓜德罗普岛", "关岛", "危地马拉", "几内亚", "几内亚比绍", "圭亚那",
            "海地", "夏威夷", "洪都拉斯", "匈牙利", "冰岛", "印度", "印度尼西亚", "伊郎", "伊拉克", "爱尔兰", "以色列",
            "意大利", "科特迪瓦", "牙买加", "日本", "约旦", "柬埔塞", "哈萨克斯坦", "肯尼亚", "基里巴斯", "朝鲜", "韩国",
            "科威特", "吉尔吉斯斯坦", "老挝", "拉脱维亚", "黎巴嫩", "莱索托", "利比里亚", "利比亚", "列支敦士登", "立陶宛",
            "卢森堡", "马其顿", "马达加斯加", "马拉维", "马来西亚", "马尔代夫", "马里", "马耳他", "马里亚纳群岛", "马绍尔群岛",
            "马提尼克", "毛里塔尼亚", "毛里求斯", "马约特岛", "墨西哥", "密克罗尼西亚", "中途岛", "摩尔多瓦", "摩纳哥", "蒙古",
            "蒙特塞拉特岛", "摩洛哥", "莫桑比克", "缅甸", "纳米比亚", "瑙鲁", "尼泊尔", "荷兰", "荷属安的列斯群岛", "新喀里多尼亚群岛",
            "新西兰", "尼加拉瓜", "尼日尔", "尼日利亚", "纽埃岛", "诺福克岛", "挪威", "阿曼", "帕劳", "巴拿马", "巴布亚新几内亚",
            "巴拉圭", "秘鲁", "菲律宾", "波兰", "葡萄牙", "巴基斯坦", "波多黎各", "卡塔尔", "留尼汪岛", "罗马尼亚", "俄罗斯",
            "卢旺达", "东萨摩亚", "西萨摩亚", "圣马力诺", "圣皮埃尔岛及密克隆岛", "圣多美和普林西比", "沙特阿拉伯", "塞内加尔",
            "塞舌尔", "新加坡", "斯洛伐克", "斯洛文尼亚", "所罗门群岛", "索马里", "南非", "西班牙", "斯里兰卡", "圣克里斯托弗和尼维斯",
            "圣赫勒拿", "圣卢西亚", "圣文森特岛", "苏丹", "苏里南", "斯威士兰", "瑞典", "瑞士", "叙利亚", "塔吉克斯坦", "坦桑尼亚",
            "泰国", "阿拉伯联合酋长国", "多哥", "托克劳群岛", "汤加", "特立尼达和多巴哥", "突尼斯", "土耳其", "土库曼斯坦",
            "特克斯和凯科斯群岛", "图瓦卢", "美国", "乌干达", "乌克兰", "英国", "乌拉圭", "乌兹别克斯坦", "瓦努阿图",
            "梵蒂冈", "委内瑞拉", "越南", "维尔京群岛", "维尔京群岛和圣罗克伊", "威克岛", "瓦里斯和富士那群岛", "西撒哈拉",
            "也门", "南斯拉夫", "扎伊尔", "赞比亚", "桑给巴尔", "津巴布韦", "中华人民共和国", "中国"};

    private static final String[] PROVINCES = {
            "北京市", "上海市", "天津市", "重庆市",
            "内蒙古自治区", "山西省", "河北省", "吉林省", "江苏省", "辽宁省", "黑龙江省",
            "安徽省", "山东省", "浙江省", "江西省", "福建省", "湖南省", "湖北省",
            "河南省", "广东省", "广西壮族自治区", "贵州省", "海南省", "四川省", "云南省",
            "陕西省", "甘肃省", "宁夏回族自治区", "青海省", "新疆维吾尔自治区", "西藏自治区",
            "台湾省", "香港特别行政区", "澳门特别行政区"};

    private static final String[] DISTRICTS = {
            "西夏", "永川", "秀英", "高港", "清城", "兴山", "锡山", "清河",
            "龙潭", "华龙", "海陵", "滨城", "东丽", "高坪", "沙湾", "平山",
            "城北", "海港", "沙市", "双滦", "长寿", "山亭", "南湖", "浔阳",
            "南长", "友好", "安次", "翔安", "沈河", "魏都", "西峰", "萧山",
            "金平", "沈北新", "孝南", "上街", "城东", "牧野", "大东",
            "白云", "花溪", "吉区", "新城", "怀柔", "六枝特", "涪城",
            "清浦", "南溪", "淄川", "高明", "东城", "崇文", "朝阳", "大兴",
            "房山", "门头沟", "黄浦", "徐汇", "静安", "普陀", "闵行", "和平",
            "蓟州", "永川", "长寿", "璧山", "合川", "梁平", "丰都", "江北"};

    private static final String[] CITIES = {
            "北京", "上海", "天津", "重庆", "哈尔滨", "长春", "沈阳", "呼和浩特",
            "石家庄", "乌鲁木齐", "兰州", "西宁", "西安", "银川", "郑州", "济南", "太原",
            "合肥", "武汉", "长沙", "南京", "成都", "贵阳", "昆明", "南宁", "拉萨",
            "杭州", "南昌", "广州", "福州", "台北", "海口", "香港", "澳门", "通辽",
            "兴安盟", "太原", "辛集", "邯郸", "沈阳", "辽阳", "兴城", "北镇", "阜新",
            "哈尔滨", "齐齐哈尔", "淮安", "张家港", "海门", "六安", "巢湖", "马鞍山",
            "永安", "宁德", "嘉禾", "荆门", "潜江", "大冶", "宜都", "佛山", "深圳",
            "潮州", "惠州", "汕尾", "东莞", "梧州", "柳州", "合山", "六盘水", "关岭"};

    private static final String[] STREETS = {
            "白山", "解放", "玉华", "集贤", "中山", "连胜", "新华", "南海", "莲花山", "白云", "碧海", "人民", "韶山",
            "海燕", "海茂", "海鸥", "七星", "六闾", "松山", "甘园", "站前", "光明", "甘中", "东山", "甘峰",
            "长江", "黄河", "泰山", "工华", "抚顺", "永安", "至诚", "民利", "友爱", "大胜", "万顺", "天兴", "永联", "万兴",
    };

    private static final String[] CITY_SUFFIXES = {"市", "县"};
    private static final String[] STREET_SUFFIXES = {"街", "路"};

    public static String nextAddress()
    {
        return String.format("%s%s%s%s区%s%s%d号", CommonUtil.randChoose(PROVINCES),
                CommonUtil.randChoose(CITIES),
                CommonUtil.randChoose(CITY_SUFFIXES),
                CommonUtil.randChoose(DISTRICTS),
                CommonUtil.randChoose(STREETS),
                CommonUtil.randChoose(STREET_SUFFIXES),
                RandomSource.XO_RO_SHI_RO_1024_PP.create().nextInt(0, 300)
        );
    }

    public static String nextCountry()
    {
        return CommonUtil.randChoose(COUNTRIES);
    }
}

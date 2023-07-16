/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                       */
/*  \   \        Copyright (c) 2003-2009 Xilinx, Inc.                */
/*  /   /          All Right Reserved.                                 */
/* /---/   /\                                                         */
/* \   \  /  \                                                      */
/*  \___\/\___\                                                    */
/***********************************************************************/

/* This file is designed for use with ISim build 0x7708f090 */

#define XSI_HIDE_SYMBOL_SPEC true
#include "xsi.h"
#include <memory.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
static const char *ng0 = "D:/UHU/FC/Practica 6 FC/Practica_6.vhd";
extern char *IEEE_P_2592010699;
extern char *IEEE_P_3620187407;

int ieee_p_3620187407_sub_514432868_3965413181(char *, char *, char *);


static void work_a_3318648661_3212880686_p_0(char *t0)
{
    char t6[16];
    char t11[16];
    char t16[16];
    char *t1;
    char *t2;
    unsigned char t3;
    char *t4;
    unsigned char t5;
    char *t7;
    char *t8;
    char *t9;
    unsigned char t10;
    char *t12;
    char *t13;
    char *t14;
    unsigned char t15;
    char *t17;
    unsigned int t18;
    unsigned int t19;
    unsigned int t20;
    unsigned char t21;
    char *t22;
    char *t23;
    char *t24;
    char *t25;
    char *t26;
    char *t27;

LAB0:    xsi_set_current_line(55, ng0);

LAB3:    t1 = (t0 + 1512U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t1 = (t0 + 1352U);
    t4 = *((char **)t1);
    t5 = *((unsigned char *)t4);
    t7 = ((IEEE_P_2592010699) + 4024);
    t1 = xsi_base_array_concat(t1, t6, t7, (char)99, t3, (char)99, t5, (char)101);
    t8 = (t0 + 1192U);
    t9 = *((char **)t8);
    t10 = *((unsigned char *)t9);
    t12 = ((IEEE_P_2592010699) + 4024);
    t8 = xsi_base_array_concat(t8, t11, t12, (char)97, t1, t6, (char)99, t10, (char)101);
    t13 = (t0 + 1032U);
    t14 = *((char **)t13);
    t15 = *((unsigned char *)t14);
    t17 = ((IEEE_P_2592010699) + 4024);
    t13 = xsi_base_array_concat(t13, t16, t17, (char)97, t8, t11, (char)99, t15, (char)101);
    t18 = (1U + 1U);
    t19 = (t18 + 1U);
    t20 = (t19 + 1U);
    t21 = (4U != t20);
    if (t21 == 1)
        goto LAB5;

LAB6:    t22 = (t0 + 6096);
    t23 = (t22 + 56U);
    t24 = *((char **)t23);
    t25 = (t24 + 56U);
    t26 = *((char **)t25);
    memcpy(t26, t13, 4U);
    xsi_driver_first_trans_fast(t22);

LAB2:    t27 = (t0 + 5920);
    *((int *)t27) = 1;

LAB1:    return;
LAB4:    goto LAB2;

LAB5:    xsi_size_not_matching(4U, t20, 0);
    goto LAB6;

}

static void work_a_3318648661_3212880686_p_1(char *t0)
{
    char *t1;
    char *t2;
    int t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    char *t9;

LAB0:    xsi_set_current_line(56, ng0);

LAB3:    t1 = (t0 + 2472U);
    t2 = *((char **)t1);
    t1 = (t0 + 9072U);
    t3 = ieee_p_3620187407_sub_514432868_3965413181(IEEE_P_3620187407, t2, t1);
    t4 = (t0 + 6160);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    *((int *)t8) = t3;
    xsi_driver_first_trans_fast(t4);

LAB2:    t9 = (t0 + 5936);
    *((int *)t9) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_3318648661_3212880686_p_2(char *t0)
{
    char t6[16];
    char *t1;
    char *t2;
    unsigned char t3;
    char *t4;
    unsigned char t5;
    char *t7;
    unsigned int t8;
    unsigned char t9;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;

LAB0:    xsi_set_current_line(57, ng0);

LAB3:    t1 = (t0 + 1832U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t1 = (t0 + 1672U);
    t4 = *((char **)t1);
    t5 = *((unsigned char *)t4);
    t7 = ((IEEE_P_2592010699) + 4024);
    t1 = xsi_base_array_concat(t1, t6, t7, (char)99, t3, (char)99, t5, (char)101);
    t8 = (1U + 1U);
    t9 = (2U != t8);
    if (t9 == 1)
        goto LAB5;

LAB6:    t10 = (t0 + 6224);
    t11 = (t10 + 56U);
    t12 = *((char **)t11);
    t13 = (t12 + 56U);
    t14 = *((char **)t13);
    memcpy(t14, t1, 2U);
    xsi_driver_first_trans_fast(t10);

LAB2:    t15 = (t0 + 5952);
    *((int *)t15) = 1;

LAB1:    return;
LAB4:    goto LAB2;

LAB5:    xsi_size_not_matching(2U, t8, 0);
    goto LAB6;

}

static void work_a_3318648661_3212880686_p_3(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    int t5;
    char *t6;
    char *t7;
    int t8;
    char *t9;
    int t11;
    char *t12;
    int t14;
    char *t15;
    char *t16;
    char *t17;
    char *t18;
    char *t19;

LAB0:    t1 = (t0 + 4856U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(59, ng0);
    t2 = (t0 + 2632U);
    t3 = *((char **)t2);
    t2 = (t0 + 9172);
    t5 = xsi_mem_cmp(t2, t3, 2U);
    if (t5 == 1)
        goto LAB5;

LAB10:    t6 = (t0 + 9174);
    t8 = xsi_mem_cmp(t6, t3, 2U);
    if (t8 == 1)
        goto LAB6;

LAB11:    t9 = (t0 + 9176);
    t11 = xsi_mem_cmp(t9, t3, 2U);
    if (t11 == 1)
        goto LAB7;

LAB12:    t12 = (t0 + 9178);
    t14 = xsi_mem_cmp(t12, t3, 2U);
    if (t14 == 1)
        goto LAB8;

LAB13:
LAB9:    xsi_set_current_line(60, ng0);
    t2 = (t0 + 6288);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = (t4 + 56U);
    t7 = *((char **)t6);
    *((int *)t7) = 0;
    xsi_driver_first_trans_fast(t2);

LAB4:    xsi_set_current_line(59, ng0);

LAB17:    t2 = (t0 + 5968);
    *((int *)t2) = 1;
    *((char **)t1) = &&LAB18;

LAB1:    return;
LAB5:    xsi_set_current_line(60, ng0);
    t15 = (t0 + 6288);
    t16 = (t15 + 56U);
    t17 = *((char **)t16);
    t18 = (t17 + 56U);
    t19 = *((char **)t18);
    *((int *)t19) = 2;
    xsi_driver_first_trans_fast(t15);
    goto LAB4;

LAB6:    xsi_set_current_line(60, ng0);
    t2 = (t0 + 6288);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = (t4 + 56U);
    t7 = *((char **)t6);
    *((int *)t7) = 3;
    xsi_driver_first_trans_fast(t2);
    goto LAB4;

LAB7:    xsi_set_current_line(60, ng0);
    t2 = (t0 + 6288);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = (t4 + 56U);
    t7 = *((char **)t6);
    *((int *)t7) = 5;
    xsi_driver_first_trans_fast(t2);
    goto LAB4;

LAB8:    xsi_set_current_line(60, ng0);
    t2 = (t0 + 6288);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = (t4 + 56U);
    t7 = *((char **)t6);
    *((int *)t7) = 6;
    xsi_driver_first_trans_fast(t2);
    goto LAB4;

LAB14:;
LAB15:    t3 = (t0 + 5968);
    *((int *)t3) = 0;
    goto LAB2;

LAB16:    goto LAB15;

LAB18:    goto LAB16;

}

static void work_a_3318648661_3212880686_p_4(char *t0)
{
    char *t1;
    char *t2;
    int t3;
    char *t4;
    int t5;
    int t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    char *t11;

LAB0:    xsi_set_current_line(66, ng0);

LAB3:    t1 = (t0 + 2312U);
    t2 = *((char **)t1);
    t3 = *((int *)t2);
    t1 = (t0 + 2792U);
    t4 = *((char **)t1);
    t5 = *((int *)t4);
    t6 = (t3 - t5);
    t1 = (t0 + 6352);
    t7 = (t1 + 56U);
    t8 = *((char **)t7);
    t9 = (t8 + 56U);
    t10 = *((char **)t9);
    *((int *)t10) = t6;
    xsi_driver_first_trans_fast(t1);

LAB2:    t11 = (t0 + 5984);
    *((int *)t11) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_3318648661_3212880686_p_5(char *t0)
{
    char *t1;
    char *t2;
    int t3;
    unsigned char t4;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    unsigned char t11;
    char *t12;
    char *t13;
    int t14;
    unsigned char t15;
    char *t16;
    int t17;
    int t18;
    unsigned char t19;
    char *t21;
    char *t22;
    char *t23;
    char *t24;
    char *t25;
    unsigned char t26;
    char *t27;
    char *t28;
    int t29;
    unsigned char t30;
    char *t31;
    int t32;
    int t33;
    unsigned char t34;
    char *t36;
    char *t37;
    char *t38;
    char *t39;
    char *t40;
    unsigned char t41;
    char *t42;
    char *t43;
    int t44;
    unsigned char t45;
    char *t46;
    int t47;
    int t48;
    unsigned char t49;
    char *t51;
    char *t52;
    char *t53;
    char *t54;
    char *t55;
    unsigned char t56;
    char *t57;
    char *t58;
    int t59;
    unsigned char t60;
    char *t61;
    int t62;
    int t63;
    unsigned char t64;
    char *t66;
    char *t67;
    char *t68;
    char *t69;
    char *t70;
    unsigned char t71;
    char *t72;
    char *t73;
    int t74;
    unsigned char t75;
    char *t76;
    int t77;
    int t78;
    unsigned char t79;
    char *t81;
    char *t82;
    char *t83;
    char *t84;
    char *t85;
    unsigned char t86;
    char *t87;
    char *t88;
    int t89;
    unsigned char t90;
    char *t91;
    int t92;
    int t93;
    unsigned char t94;
    char *t96;
    char *t97;
    char *t98;
    char *t99;
    char *t100;
    unsigned char t101;
    char *t102;
    char *t103;
    int t104;
    unsigned char t105;
    char *t106;
    int t107;
    int t108;
    unsigned char t109;
    char *t111;
    char *t112;
    char *t113;
    char *t114;
    char *t115;
    char *t116;
    char *t118;
    char *t119;
    char *t120;
    char *t121;
    char *t122;
    char *t123;

LAB0:    xsi_set_current_line(68, ng0);
    t1 = (t0 + 2952U);
    t2 = *((char **)t1);
    t3 = *((int *)t2);
    t4 = (t3 == 0);
    if (t4 != 0)
        goto LAB3;

LAB4:    t12 = (t0 + 2952U);
    t13 = *((char **)t12);
    t14 = *((int *)t13);
    t15 = (t14 == 1);
    if (t15 == 1)
        goto LAB7;

LAB8:    t12 = (t0 + 2952U);
    t16 = *((char **)t12);
    t17 = *((int *)t16);
    t18 = (-(1));
    t19 = (t17 == t18);
    t11 = t19;

LAB9:    if (t11 != 0)
        goto LAB5;

LAB6:    t27 = (t0 + 2952U);
    t28 = *((char **)t27);
    t29 = *((int *)t28);
    t30 = (t29 == 2);
    if (t30 == 1)
        goto LAB12;

LAB13:    t27 = (t0 + 2952U);
    t31 = *((char **)t27);
    t32 = *((int *)t31);
    t33 = (-(2));
    t34 = (t32 == t33);
    t26 = t34;

LAB14:    if (t26 != 0)
        goto LAB10;

LAB11:    t42 = (t0 + 2952U);
    t43 = *((char **)t42);
    t44 = *((int *)t43);
    t45 = (t44 == 3);
    if (t45 == 1)
        goto LAB17;

LAB18:    t42 = (t0 + 2952U);
    t46 = *((char **)t42);
    t47 = *((int *)t46);
    t48 = (-(3));
    t49 = (t47 == t48);
    t41 = t49;

LAB19:    if (t41 != 0)
        goto LAB15;

LAB16:    t57 = (t0 + 2952U);
    t58 = *((char **)t57);
    t59 = *((int *)t58);
    t60 = (t59 == 4);
    if (t60 == 1)
        goto LAB22;

LAB23:    t57 = (t0 + 2952U);
    t61 = *((char **)t57);
    t62 = *((int *)t61);
    t63 = (-(4));
    t64 = (t62 == t63);
    t56 = t64;

LAB24:    if (t56 != 0)
        goto LAB20;

LAB21:    t72 = (t0 + 2952U);
    t73 = *((char **)t72);
    t74 = *((int *)t73);
    t75 = (t74 == 5);
    if (t75 == 1)
        goto LAB27;

LAB28:    t72 = (t0 + 2952U);
    t76 = *((char **)t72);
    t77 = *((int *)t76);
    t78 = (-(5));
    t79 = (t77 == t78);
    t71 = t79;

LAB29:    if (t71 != 0)
        goto LAB25;

LAB26:    t87 = (t0 + 2952U);
    t88 = *((char **)t87);
    t89 = *((int *)t88);
    t90 = (t89 == 6);
    if (t90 == 1)
        goto LAB32;

LAB33:    t87 = (t0 + 2952U);
    t91 = *((char **)t87);
    t92 = *((int *)t91);
    t93 = (-(6));
    t94 = (t92 == t93);
    t86 = t94;

LAB34:    if (t86 != 0)
        goto LAB30;

LAB31:    t102 = (t0 + 2952U);
    t103 = *((char **)t102);
    t104 = *((int *)t103);
    t105 = (t104 == 7);
    if (t105 == 1)
        goto LAB37;

LAB38:    t102 = (t0 + 2952U);
    t106 = *((char **)t102);
    t107 = *((int *)t106);
    t108 = (-(7));
    t109 = (t107 == t108);
    t101 = t109;

LAB39:    if (t101 != 0)
        goto LAB35;

LAB36:
LAB40:    t116 = (t0 + 9236);
    t118 = (t0 + 6416);
    t119 = (t118 + 56U);
    t120 = *((char **)t119);
    t121 = (t120 + 56U);
    t122 = *((char **)t121);
    memcpy(t122, t116, 7U);
    xsi_driver_first_trans_fast_port(t118);

LAB2:    t123 = (t0 + 6000);
    *((int *)t123) = 1;

LAB1:    return;
LAB3:    t1 = (t0 + 9180);
    t6 = (t0 + 6416);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    t9 = (t8 + 56U);
    t10 = *((char **)t9);
    memcpy(t10, t1, 7U);
    xsi_driver_first_trans_fast_port(t6);
    goto LAB2;

LAB5:    t12 = (t0 + 9187);
    t21 = (t0 + 6416);
    t22 = (t21 + 56U);
    t23 = *((char **)t22);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    memcpy(t25, t12, 7U);
    xsi_driver_first_trans_fast_port(t21);
    goto LAB2;

LAB7:    t11 = (unsigned char)1;
    goto LAB9;

LAB10:    t27 = (t0 + 9194);
    t36 = (t0 + 6416);
    t37 = (t36 + 56U);
    t38 = *((char **)t37);
    t39 = (t38 + 56U);
    t40 = *((char **)t39);
    memcpy(t40, t27, 7U);
    xsi_driver_first_trans_fast_port(t36);
    goto LAB2;

LAB12:    t26 = (unsigned char)1;
    goto LAB14;

LAB15:    t42 = (t0 + 9201);
    t51 = (t0 + 6416);
    t52 = (t51 + 56U);
    t53 = *((char **)t52);
    t54 = (t53 + 56U);
    t55 = *((char **)t54);
    memcpy(t55, t42, 7U);
    xsi_driver_first_trans_fast_port(t51);
    goto LAB2;

LAB17:    t41 = (unsigned char)1;
    goto LAB19;

LAB20:    t57 = (t0 + 9208);
    t66 = (t0 + 6416);
    t67 = (t66 + 56U);
    t68 = *((char **)t67);
    t69 = (t68 + 56U);
    t70 = *((char **)t69);
    memcpy(t70, t57, 7U);
    xsi_driver_first_trans_fast_port(t66);
    goto LAB2;

LAB22:    t56 = (unsigned char)1;
    goto LAB24;

LAB25:    t72 = (t0 + 9215);
    t81 = (t0 + 6416);
    t82 = (t81 + 56U);
    t83 = *((char **)t82);
    t84 = (t83 + 56U);
    t85 = *((char **)t84);
    memcpy(t85, t72, 7U);
    xsi_driver_first_trans_fast_port(t81);
    goto LAB2;

LAB27:    t71 = (unsigned char)1;
    goto LAB29;

LAB30:    t87 = (t0 + 9222);
    t96 = (t0 + 6416);
    t97 = (t96 + 56U);
    t98 = *((char **)t97);
    t99 = (t98 + 56U);
    t100 = *((char **)t99);
    memcpy(t100, t87, 7U);
    xsi_driver_first_trans_fast_port(t96);
    goto LAB2;

LAB32:    t86 = (unsigned char)1;
    goto LAB34;

LAB35:    t102 = (t0 + 9229);
    t111 = (t0 + 6416);
    t112 = (t111 + 56U);
    t113 = *((char **)t112);
    t114 = (t113 + 56U);
    t115 = *((char **)t114);
    memcpy(t115, t102, 7U);
    xsi_driver_first_trans_fast_port(t111);
    goto LAB2;

LAB37:    t101 = (unsigned char)1;
    goto LAB39;

LAB41:    goto LAB2;

}

static void work_a_3318648661_3212880686_p_6(char *t0)
{
    char *t1;
    char *t2;
    int t3;
    char *t4;
    int t5;
    unsigned char t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;

LAB0:    xsi_set_current_line(78, ng0);
    t1 = (t0 + 2792U);
    t2 = *((char **)t1);
    t3 = *((int *)t2);
    t1 = (t0 + 2312U);
    t4 = *((char **)t1);
    t5 = *((int *)t4);
    t6 = (t3 > t5);
    if (t6 != 0)
        goto LAB3;

LAB4:
LAB5:    t11 = (t0 + 6480);
    t12 = (t11 + 56U);
    t13 = *((char **)t12);
    t14 = (t13 + 56U);
    t15 = *((char **)t14);
    *((unsigned char *)t15) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t11);

LAB2:    t16 = (t0 + 6016);
    *((int *)t16) = 1;

LAB1:    return;
LAB3:    t1 = (t0 + 6480);
    t7 = (t1 + 56U);
    t8 = *((char **)t7);
    t9 = (t8 + 56U);
    t10 = *((char **)t9);
    *((unsigned char *)t10) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB6:    goto LAB2;

}


extern void work_a_3318648661_3212880686_init()
{
	static char *pe[] = {(void *)work_a_3318648661_3212880686_p_0,(void *)work_a_3318648661_3212880686_p_1,(void *)work_a_3318648661_3212880686_p_2,(void *)work_a_3318648661_3212880686_p_3,(void *)work_a_3318648661_3212880686_p_4,(void *)work_a_3318648661_3212880686_p_5,(void *)work_a_3318648661_3212880686_p_6};
	xsi_register_didat("work_a_3318648661_3212880686", "isim/Practica6_test_isim_beh.exe.sim/work/a_3318648661_3212880686.didat");
	xsi_register_executes(pe);
}

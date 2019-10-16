package com.wy.juclearn.five.immutability;

/**
 * @author wy
 * @Classname ImmutabilityPatternDemo
 * @Description
 *  不变模式：
 * 在并行软件开发过程中，同步操作似乎是必不可少的。当多线程对同一个对象进行读写操作时，为了保证对象数据的一致性和正确性，有必须要对对象进行同步。
 * 而同步操作对系统性能是有相当的损耗。为了能尽可能地去除这些同步操作，提高并行程序性能，可以使用一种不可改变的对象，依靠对象的不变性，可以确保其在没有同步操作的多线程环境中
 * 依然始终保持内部状态的一致性和正确性。这就是不变模式
 *
 * 不变模式天生就是多线程友好的
 * 核心思想：一个对象一旦创建，则它的内部状态将永远不会发生改变。所以，没有一个县城可以修改其内部状态和数据，同时其内部状态也绝不会自行发生改变。基于这些特性，对不可变对象的多线程操作不需要
 * 进行同步控制。
 *
 * 同时还需要注意，不变模式和只读属性是有一定区别的。不变模式是比只读属性具有更强的一致性和不变性。对只读属性的对象而言，对象本身不能被其他线程修改，但是对象的自身状态却可能自行修改。
 *
 *不变模式的主要使用场景需要满足以下两个条件：
 * 1.当对象创建后，其内部状态和数据不再发生任何变化。
 * 2.对象需要被共享，被多线程频繁访问
 *
 * 在java语言中，不变模式的实现很简单。为确保对象创建后，不发生任何改变，并保证不变模式的正常工作，需要注意以下4点：
 * 1.去除setter方法以及所有修改自身属性的方法
 * 2.将所有属性设置为私有，并用final标记，确保其不可修改
 * 3.确保没有子类可以重载修改它的行为。
 * 4.有一个可以创建完成对象的构造函数。
 *
 *
 * 在不变模式的实现中，final关键字起到了重要的作用。对属性的final定义确保所有数据只能在对象被构造时赋值一次。
 * 之后，就永远不再发生改变，而对class的final确保了类不会有子类。根据里氏替换原则，子类可以完全代替父类。如果父类是不变的，
 * 那么子类有必须是不变的，但实际上并无法约束这一点，为了防止子类可以做出一些意外的行为，就直接把这一特性禁用掉。
 *
 * 在jdk中，不变模式的应用非常广泛。其中，最为典型的就是String类。
 * 由于基本数据类型和String类型咋实际的开发中应用非常广泛，使用不变模式后，所有的方法均不需要进行同步操作，保证了他们在多线程环境下的性能问题。
 *
 *
 * 注意点：
 * 不变模式通过回避问题而不是解决问题的态度来处理多线程并发访问控制。不变对象是不需要进行同步操作的。由于并发哦同步会对性能产生不良的影响，因此，在需求允许的情况下，
 * 不变模式可以提高系统的并发性能和并发量。
 * @Date 2019/10/11 3:46 下午
 */
public final class ImmutabilityPatternDemo {
    private final String id;
    private final String name;
    private final double price;


    public ImmutabilityPatternDemo(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static void main(String[] args) {
        System.out.println("ImmutabilityPatternDemo");
    }
}

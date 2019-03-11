package Interfaces;

public interface ICreator {
    static IProblem create(int topic, int number){
        switch(topic){
            case 1:
            {
                switch(number){
                    case 1: return new Casting.Basics.Problem();
                    case 2: return new Casting.InstanceOf.Problem();
                }
            }
            case 4:
            {
                switch(number){
                    case 1: return new Classes.InnerClasses.Problem();
                    case 2: return new Classes.StaticMethods.Problem();
                    case 3: return new Classes.VarArgs.Problem();
                    case 4: return new Classes.AnonymousClasses.Problem();
                    case 5: return new Classes.AccessLevels.Problem();
                    case 6: return new Classes.AbstractClasses.Problem();
                    case 7: return new Classes.InnerAndNestedClasses.Problem();
                }
            }
            case 5:
            {
                switch(number){
                    case 1: return new Polymorphism.InheritanceAndReferences.Problem();
                    case 2: return new Polymorphism.PolymorphismAndParameters.Problem();
                    case 3: return new Polymorphism.FinalMethods.Problem();
                }
            }
            case 6:
            {
                switch(number){
                    case 1: return new InterfacesProblems.Problem();
                    case 2: return new InterfacesProblems.ComparableVSComparator.Problem();
                    case 3: return new InterfacesProblems.Variables.Problem();
                }
            }
            case 7:
            {
                switch(number){
                    case 1: return new Exceptions.Basics.Problem();
                    case 2: return new Exceptions.Inheritance.Problem();
                }
            }
            case 8:
            {
                switch(number){
                    case 1: return new Threads.ThreadsBasics.Problem();
                    case 2: return new Threads.ThreadsRunnable.Problem();
                    case 3: return new Threads.ThreadsGeneralization.Problem();
                    case 4: return new Threads.WaitNotify.Problem();
                    case 5: return new Threads.ThreadsExecutor.Problem();
                    case 6: return new Threads.DeadLock.Problem();
                    case 7: return new Threads.Callable.Problem();
                    case 8: return new Threads.Semaphore.Problem();
                    case 9: return new Threads.SemaphoreSynchronyzing.Problem();
                    case 10: return new Threads.CountDownLatch.Problem();
                    case 11: return new Threads.Exchanger.Problem();
                    case 12: return new Threads.CyclicBarrier.Problem();
                    case 13: return new Threads.Phaser.Problem();
                    case 14: return new Threads.java.util.concurrent.lock.Problem();
                    case 15: return new Threads.PhaserRevelation.Problem();
                }
            }
            case 9:
            {
                switch(number){
                    case 1: return new Enums.Basics.Problem();
                    case 2: return new Enums.EnumFactory.Problem();
                    case 3: return new PackUnpack.Problem();
                    case 4: return new Annotations.Basics.Problem();
                    case 5: return new Annotations.DefaultValues.Problem();
                    case 6: return new Annotations.MarkersAndOneMember.Problem();
                    case 7: return new Annotations.BuildInAnno.Problem();
                }
            }
            case 10:
            {
                switch(number){
                    case 1: return new IO.Problem();
                    case 2: return new Assert.Problem();
                    case 3: return new IO.FileDemo.Problem();
                    case 4: return new IO.Flushable.Problem();
                    case 5: return new IO.Console.Problem();
                    case 6: return new IO.NIO_Buffer.Problem();
                    case 7: return new IO.NIO_Channel.Problem();
                    case 8: return new IO.NIO_Path_Files.Problem();
                    case 9: return new IO.CrapWithSystemIn.Problem();
                }
            }
            case 11:
            {
                switch(number){
                    case 1: return new Generics.MetaSymbol.Problem();
                    case 2: return new Generics.GenericInterface.Problem();
                    case 3: return new Generics.GenericsRestrictions.Problem();
                    case 4: return new Generics.TExtendsComparableT.Problem();
                    case 5: return new Generics.MetaSymbol_Revelation.Problem();
                    case 6: return new Generics.Wildcards.Problem();
                    case 7: return new Generics.WildcardsRevelation.Problem();
                }
            }
            case 12:
            {
                switch(number){
                    case 1: return new Reflection.Problem();
                }
            }
            case 13:
            {
                switch(number){
                    case 1: return new Lambdas.Basics.Problem();
                    case 2: return new Lambdas.NoneStatMethRef.Problem();
                    case 3: return new Lambdas.PredefinedFunctionalInterfaces.Problem();
                }
            }
            case 14:
            {
                switch(number){
                    case 1: return new Patterns.Singleton.Problem();
                    case 2: return new Patterns.SingletonEnum.Problem();
                    case 3: return new Patterns.Builder.Problem();
                    case 4: return new Patterns.Decorator.Problem();
                    case 5: return new Patterns.Factory.Problem();
                }
            }
            case 15:
                {
                switch(number){
                    case 1: return new StringLib.Problem();
                }
            }
            case 16:
            {
                switch(number){
                    case 1: return new Serialization.Serialization_Standard.Problem();
                    case 2: return new Serialization.Serialization_WriteObject.Problem();
                    case 3: return new Serialization.Extern.Problem();
                }
            }
            case 17:
            {
                switch(number){
                    case 1: return new JavaLang.Basics.Problem();
                    case 2: return new JavaLang.ClassVoid.Problem();
                }
            }
            case 18:
            {
                switch(number){
                    case 1: return new Collections.Basics.Problem();
                    case 2: return new Collections.Iterators.Problem();
                    case 3: return new Collections.Maps.Problem();
                    case 4: return new Collections.Arrays.Problem();
                    case 5: return new Collections.Spliterators.Problem();
                    case 6: return new Collections.FromInterviews.Problem();
                }
            }
            case 19:
            {
                switch(number){
                    case 1: return new JavaUtil.Optional.Problem();
                    case 2: return new JavaUtil.Observable.Problem();
                    case 3: return new JavaUtil.BitSetClass.Problem();
                    case 4: return new JavaUtil.CalendarClass.Problem();
                    case 5: return new JavaUtil.FormatterScanner.Problem();
                }
            }
            case 20:
            {
                switch(number){
                    case 1: return new JavaNet.InetAddress.Problem();
                    case 2: return new JavaNet.Socket.Problem();
                    case 3: return new JavaNet.URL.Problem();
                    case 4: return new JavaNet.ServerClient.Problem();
                }
            }
            case 21:
            {
                switch(number){
                    case 1: return new JDBC.Basics.Problem();
                }
            }
            case 22:
            {
                switch(number){
                    case 1: return new Stream_API.Basics.Problem();
                }
            }
            case 23:
            {
                switch(number){
                    case 1: return new regexp.basics.Problem();
                }
            }
            default:
                return null;
        }
    }
}
package listeners

import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan
import utils.CleanUpSteps
import utils.ConfigurationLoader
import utils.EnvConfig
import utils.PrepareSteps

class TestListener : TestExecutionListener {

    override fun testPlanExecutionStarted(testPlan: TestPlan?) {
        println("|----- CONFIGURATION -----|\n")

        println("\n|----- LOAD ENV CONFIGURATION -----|\n")
        ConfigurationLoader.loadProperties()

        println("\n|----- GENERATE A GLOBAL TOKEN-----|\n")
        EnvConfig.token = PrepareSteps.getJWTToken()

        println("\n|----- CREATE A CONNECTION -----|\n")
        EnvConfig.data = PrepareSteps.prepareData()

        println("\n|----- START -----|\n")
    }

    override fun executionStarted(testIdentifier: TestIdentifier?) {
        if (testIdentifier!!.isTest) println("Start: ${testIdentifier.displayName}")
    }

    override fun executionFinished(testIdentifier: TestIdentifier?, testExecutionResult: TestExecutionResult?) {
        if (testIdentifier!!.isTest) println("Finish: ${testIdentifier.displayName}")
        if (testExecutionResult!!.status == TestExecutionResult.Status.FAILED && testIdentifier.displayName != "JUnit Jupiter") {
            //FAILED TEST DESCRIPTION
        }
    }

    override fun executionSkipped(testIdentifier: TestIdentifier?, reason: String?) {
        if (testIdentifier!!.isTest) println("Skip: ${testIdentifier.displayName}\nReason: $reason")
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan?) {
        println("\n|----- FINISH -----|\n")

        println("\n|----- CLEAN UP  -----|\n")
        CleanUpSteps.cleanUp()
    }
}
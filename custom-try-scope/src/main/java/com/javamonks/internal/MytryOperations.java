package com.javamonks.internal;

import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.mule.runtime.extension.api.runtime.route.Chain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javamonks.common.Execuatable;
import com.javamonks.common.Executor;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */
public class MytryOperations // extends AbstractMessageProcessorOwner implements Scope
{

	private static final Logger LOGGER = LoggerFactory.getLogger(MytryOperations.class);

	public void myTryScope(@org.mule.runtime.extension.api.annotation.param.Optional Executor instance, Chain operations,
			CompletionCallback<Object, Object> callback)  {
		if (instance == null) {
			throw new RuntimeException("executor can't be null");
		}
		instance.execute(new Execuatable() {
			
			@Override
			public void run() {
				operations.process(result -> {
					LOGGER.info("{}", result.getOutput());
					callback.success(result);
				}, (error, previous) -> {
					LOGGER.error(error.getMessage());
					callback.error(error);
				});				
			}
		});
		
	}

	/*
	 * protected MessageProcessorChain nestedChain; protected MuleTransactionConfig
	 * transactionConfig; private FlowExceptionHandler messagingExceptionHandler;
	 * private List<Processor> processors;
	 * 
	 * public MytryOperations() { }
	 * 
	 * public CoreEvent process(final CoreEvent event) throws MuleException { return
	 * MessageProcessors.processToApply(event, this); }
	 * 
	 * public Publisher<CoreEvent> apply(Publisher<CoreEvent> publisher) { if
	 * (this.transactionConfig.getAction() != 6) { ExecutionTemplate<CoreEvent>
	 * executionTemplate = TransactionalExecutionTemplate
	 * .createScopeTransactionalExecutionTemplate(this.muleContext,
	 * this.transactionConfig); I18nMessage txErrorMessage =
	 * CoreMessages.errorInvokingMessageProcessorWithinTransaction(this.nestedChain,
	 * this.transactionConfig); return Mono.subscriberContext().flatMapMany((ctx) ->
	 * { return Flux.from(publisher).handle((event, sink) -> { boolean
	 * txPrevoiuslyActive = TransactionCoordination.isTransactionActive();
	 * Transaction previousTx = this.getCurrentTx();
	 * 
	 * try { sink.next(executionTemplate.execute(() -> {
	 * this.handlePreviousTransaction(txPrevoiuslyActive, previousTx,
	 * this.getCurrentTx()); return this.processBlocking(ctx, event); })); } catch
	 * (Exception var10) { Throwable unwrapped = Exceptions.unwrap(var10); if
	 * (unwrapped instanceof MuleException) { sink.error(unwrapped); } else {
	 * sink.error(new DefaultMuleException(txErrorMessage, unwrapped)); } }
	 * 
	 * }); }); } else { return Flux.from(publisher).transform(this.nestedChain); } }
	 * 
	 * private void handlePreviousTransaction(final boolean txPrevoiuslyActive,
	 * Transaction previousTx, Transaction currentTx) { if (!txPrevoiuslyActive &&
	 * TransactionCoordination.isTransactionActive() || txPrevoiuslyActive &&
	 * previousTx != currentTx) { TransactionAdapter transaction =
	 * (TransactionAdapter) currentTx;
	 * transaction.setComponentLocation(this.getLocation()); }
	 * 
	 * }
	 * 
	 * private CoreEvent processBlocking(Context ctx, CoreEvent event) throws
	 * MuleException { try { return (CoreEvent)
	 * Mono.just(event).subscriberContext(ReactorTransactionUtils.
	 * popTxFromSubscriberContext())
	 * .transform(this.nestedChain).onErrorStop().subscriberContext((innerCtx) -> {
	 * return innerCtx.put("messageProcessors.withinProcessToApply", true); })
	 * .subscriberContext(
	 * ReactorTransactionUtils.pushTxToSubscriberContext(this.getLocation().
	 * getLocation())) .subscriberContext(ctx).block(); } catch (Throwable var4) {
	 * if (var4.getCause() instanceof InterruptedException) {
	 * Thread.currentThread().interrupt(); }
	 * 
	 * throw Exceptions.rxExceptionToMuleException(var4); } }
	 * 
	 * private Transaction getCurrentTx() { return
	 * TransactionCoordination.getInstance().getTransaction(); }
	 * 
	 * public void setExceptionListener(FlowExceptionHandler exceptionListener) {
	 * this.messagingExceptionHandler = exceptionListener; }
	 * 
	 * public void setTransactionConfig(MuleTransactionConfig transactionConfig) {
	 * this.transactionConfig = transactionConfig; }
	 * 
	 * public MuleTransactionConfig getTransactionConfig() { return
	 * this.transactionConfig; }
	 * 
	 * public void setMessageProcessors(List<Processor> processors) {
	 * this.processors = processors; }
	 * 
	 * public void initialise() throws InitialisationException { if
	 * (this.messagingExceptionHandler == null) { this.messagingExceptionHandler =
	 * this.muleContext
	 * .getDefaultErrorHandler(Optional.of(this.getRootContainerLocation().toString(
	 * ))); if (this.shouldSetLocation()) { ((ErrorHandler)
	 * this.messagingExceptionHandler).setExceptionListenersLocation(this.
	 * getLocation()); } }
	 * 
	 * this.nestedChain = MessageProcessors.buildNewChainWithListOfProcessors(
	 * MessageProcessors.getProcessingStrategy(this.locator, this), this.processors,
	 * this.messagingExceptionHandler, this.getLocation().getLocation());
	 * LifecycleUtils.initialiseIfNeeded(this.messagingExceptionHandler, true,
	 * this.muleContext); this.transactionConfig.setMuleContext(this.muleContext);
	 * super.initialise(); }
	 * 
	 * private boolean shouldSetLocation() { return
	 * (!TemplateOnErrorHandler.reuseGlobalErrorHandler() ||
	 * !(this.messagingExceptionHandler instanceof GlobalErrorHandler)) &&
	 * this.messagingExceptionHandler instanceof ErrorHandler; }
	 * 
	 * public void dispose() {
	 * LifecycleUtils.disposeIfNeeded(this.messagingExceptionHandler, LOGGER);
	 * super.dispose(); }
	 * 
	 * public void start() throws MuleException {
	 * LifecycleUtils.startIfNeeded(this.messagingExceptionHandler); super.start();
	 * }
	 * 
	 * public void stop() throws MuleException {
	 * LifecycleUtils.stopIfNeeded(this.messagingExceptionHandler); super.stop(); }
	 * 
	 * protected List<Processor> getOwnedMessageProcessors() { return
	 * Collections.singletonList(this.nestedChain); }
	 * 
	 * public ProcessingType getProcessingType() { byte txAction =
	 * this.transactionConfig.getAction(); return txAction != 1 && txAction != 2 ?
	 * ProcessingType.CPU_LITE : ProcessingType.BLOCKING; }
	 */
}

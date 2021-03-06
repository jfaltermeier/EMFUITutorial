/**
 */
package org.eclipse.example.bowling.provider;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.example.bowling.BowlingPackage;
import org.eclipse.example.bowling.Game;
import org.eclipse.example.bowling.Player;

/**
 * This is the item provider adapter for a {@link org.eclipse.example.bowling.Game} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GameItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GameItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addPlayerPropertyDescriptor(object);
			addFramesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Player feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPlayerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Game_player_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Game_player_feature", "_UI_Game_type"),
				 BowlingPackage.Literals.GAME__PLAYER,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Frames feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFramesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Game_frames_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Game_frames_feature", "_UI_Game_type"),
				 BowlingPackage.Literals.GAME__FRAMES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns Game.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Game"));
	}
	
	private Map<Game, Player> observedPlayers = new HashMap<Game, Player>();
	private Map<Game, INotifyChangedListener> installedListeners = new HashMap<Game, INotifyChangedListener>();

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		Game game = (Game) object;
		Player currentPlayer = game.getPlayer();
		Player observedPlayer = observedPlayers.get(game);
		INotifyChangedListener listener = installedListeners.get(game);
		if (observedPlayer != currentPlayer) {
			if (observedPlayer != null) {
				getPlayerItemProvider(observedPlayer).removeListener(listener);
				observedPlayers.remove(game);
				installedListeners.remove(listener);
			}
			if (currentPlayer != null) {
				listener = createNotificationListener(game);
				getPlayerItemProvider(currentPlayer).addListener(listener);
				observedPlayers.put(game, currentPlayer);
				installedListeners.put(game, listener);
			}
		}
		if (currentPlayer != null) {
			return "Game of " + getPlayerItemProvider(currentPlayer).getText(currentPlayer);
		} else {
			return getString("_UI_Game_type");
		}
	}

	private PlayerItemProvider getPlayerItemProvider(Player player) {
		return (PlayerItemProvider) adapterFactory.adapt(player, PlayerItemProvider.class);
	}

	private INotifyChangedListener createNotificationListener(Game game) {
		return new INotifyChangedListener() {
			@Override
			public void notifyChanged(Notification notification) {
				if (notification.getNotifier() == game.getPlayer()) {
					fireNotifyChanged(new ViewerNotification(notification, game, false, true));
				}
			}
		};
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Game.class)) {
			case BowlingPackage.GAME__FRAMES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case BowlingPackage.GAME__PLAYER:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return BowlingEditPlugin.INSTANCE;
	}

}
